package com.zohaltech.app.corevocabulary.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.CallSuper;
import android.util.Log;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.classes.DialogManager;
import com.zohaltech.app.corevocabulary.classes.MyRuntimeException;
import com.zohaltech.app.corevocabulary.classes.WebApiClient;
import com.zohaltech.app.corevocabulary.data.SystemSettings;
import com.zohaltech.app.corevocabulary.util.IabHelper;
import com.zohaltech.app.corevocabulary.util.IabResult;
import com.zohaltech.app.corevocabulary.util.Inventory;
import com.zohaltech.app.corevocabulary.util.Purchase;

import widgets.MyToast;


public abstract class PaymentActivity extends EnhancedActivity {

    protected final int    RC_REQUEST  = 10001;
    private final   String PAY_LOAD    = "COREVOCABULARY_ANDROID_APP";
    private final   String TAG         = "COREVOCABULARY_TAG";
    private final   String SKU_PREMIUM = "CORE_PREMIUM";
    String responseMessage = "ارتقای برنامه با مشکل مواجه شد";
    Dialog paymentDialog;
    private ProgressDialog progressDialog;
    private boolean mIsPremium = false;
    private IabHelper mHelper;
    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            Log.i(TAG, "Query inventory finished.");
            if (result.isFailure()) {
                Log.i(TAG, "Failed to query inventory: " + result);
                //complain("خطا در خرید از " + App.marketName);
            } else {
                Log.i(TAG, "Query inventory was successful.");
                // does the user have the premium upgrade?
                mIsPremium = inventory.hasPurchase(SKU_PREMIUM);

                // update UI accordingly
                if (mIsPremium) {
                    SystemSettings.register(SystemSettings.getCurrentSettings());
                    updateUiToPremiumVersion();
                    WebApiClient.sendUserData(WebApiClient.PostAction.REGISTER, inventory.getPurchase(SKU_PREMIUM).getToken());
                    setWaitScreen(false);
                    responseMessage = "شما قبلا نسخه کامل را خریده اید و به نسخه کامل ارتقا یافتید";
                    MyToast.show(responseMessage, Toast.LENGTH_LONG);
                }
                Log.i(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));
            }
            Log.i(TAG, "Initial inventory query finished; enabling main UI.");
        }
    };

    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isFailure()) {
                Log.e("PAYMENT", "Error purchasing: " + result);
                if (!"".equals(responseMessage)) {
                    complain(responseMessage);
                    responseMessage = "ارتقای برنامه با مشکل مواجه شد";
                }
            } else if (purchase.getSku().equals(SKU_PREMIUM)) {
                if (!verifyDeveloperPayload(purchase)) {
                    Log.e("PAYMENT", "Error purchasing. Authenticity verification failed.");
                    complain("خطا در ورود به حساب کاربری " + App.marketName);
                } else {
                    // give user access to premium content and update the UI

                    SystemSettings.register(SystemSettings.getCurrentSettings());
                    MyToast.show(responseMessage, Toast.LENGTH_LONG);
                    updateUiToPremiumVersion();
                    WebApiClient.sendUserData(WebApiClient.PostAction.REGISTER, purchase.getToken());
                }
            }
            setWaitScreen(false);
        }
    };

    //@Override
    //public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    //    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    //    switch (requestCode) {
    //        case 1: {
    //            // If request is cancelled, the result arrays are empty.
    //            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
    //
    //                // permission was granted, yay! Do the
    //                // contacts-related task you need to do.
    //
    //            } else {
    //
    //                // permission denied, boo! Disable the
    //                // functionality that depends on this permission.
    //            }
    //            return;
    //        }
    //
    //        // other 'case' lines to check for other
    //        // permissions this app might request
    //    }
    //}

    @Override
    @CallSuper
    protected void onCreated() {
        if (!SystemSettings.getCurrentSettings().isPremium()) {
            try {
                //
                //ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE",
                //                                                     "com.farsitel.bazaar.permission.PAY_THROUGH_BAZAAR",
                //                                                     "android.permission.READ_PHONE_STATE"}, 1);
                //// Here, thisActivity is the current activity
                //if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //
                //    // Should we show an explanation?
                //    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                //
                //        // Show an expanation to the user *asynchronously* -- don't block
                //        // this thread waiting for the user's response! After the user
                //        // sees the explanation, try again to request the permission.
                //
                //    } else {
                //
                //        // No explanation needed, we can request the permission.
                //
                //        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                //
                //        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                //        // app-defined int constant. The callback method gets the
                //        // result of the request.
                //    }
                //}

                mHelper = new IabHelper(this, App.marketPublicKey);
                //Log.d(TAG, "Starting setup.");
                mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                    public void onIabSetupFinished(IabResult result) {
                        Log.d(TAG, "Setup finished.");

                        if (!result.isSuccess()) {
                            // Oh noes, there was a problem.
                            //complain("Problem setting up in-app billing: " + result);
                            return;
                        }

                        // Have we been disposed of in the meantime? If so, quit.
                        if (mHelper == null) return;

                        // IAB is fully set up. Now, let's get an inventory of stuff we own.
                        Log.d(TAG, "Setup successful. Querying inventory.");
                        mHelper.queryInventoryAsync(mGotInventoryListener);
                    }
                });
            } catch (MyRuntimeException e) {
                //Log.e(TAG, "برنامه " + App.marketName + " نصب نیست");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
        if (data != null) {
            if (data.getExtras() != null) {
                int responseCode = data.getExtras().getInt("RESPONSE_CODE");
                if (responseCode == 0) {
                    responseMessage = "شما با موفقیت به نسخه کامل ارتقا یافتید";
                } else if (responseCode == 1) {
                    responseMessage = "";
                } else if (responseCode == 6) {
                    responseMessage = "خطا در هنگام انجام عملیات پرداخت";
                } else if (responseCode == 7) {
                    responseMessage = "خطا در خرید به دلیل اینکه این محصول در حال حاضر در «مالکیت» کاربر است";
                }
            }
        } else {
            responseMessage = "";
        }
        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.d(TAG, "onActivityResult handled by IABUtil.");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (mHelper != null) {
                mHelper.dispose();
            }
            mHelper = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean verifyDeveloperPayload(Purchase p) {
        if (p.getDeveloperPayload().equals(PAY_LOAD)) {
            return true;
        }
        return false;
    }

    private void complain(String message) {
        MyToast.show(message, Toast.LENGTH_LONG, R.drawable.ic_warning_white);
    }

    public void pay() {
        setWaitScreen(true);
        try {
            mHelper.launchPurchaseFlow(this, SKU_PREMIUM, RC_REQUEST, mPurchaseFinishedListener, PAY_LOAD);
        } catch (MyRuntimeException | IllegalStateException e) {
            Log.e(TAG, "Error : " + e.getMessage());
            e.printStackTrace();
            setWaitScreen(false);
            updateUiToTrialVersion();
            MyToast.show("خطا در ارتباط با " + App.marketName + "، لطفا بعدا دوباره تلاش کنید", Toast.LENGTH_SHORT, R.drawable.ic_warning_white);
        }
    }

    private void setWaitScreen(boolean wait) {
        if (wait) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("لطفا کمی صبر کنید...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        }
    }

    public void showPaymentDialog() {
        destroyPaymentDialog();
        paymentDialog = DialogManager.getPopupDialog(this,
                                                     "Oops!",
                                                     "To use full feature Core Vocabulary, you should upgrade to premium version.",
                                                     "UPGRADE TO PREMIUM",
                                                     "MAYBE LATER",
                                                     null,
                                                     new Runnable() {
                                                         @Override
                                                         public void run() {
                                                             pay();
                                                         }
                                                     },
                                                     new Runnable() {
                                                         @Override
                                                         public void run() {
                                                             paymentDialog.dismiss();
                                                         }
                                                     });
        paymentDialog.show();
    }

    public void destroyPaymentDialog() {
        if (paymentDialog != null) {
            if (paymentDialog.isShowing()) {
                paymentDialog.dismiss();
            }
            paymentDialog = null;
        }
    }

    @Override
    protected abstract void onToolbarCreated();

    protected abstract void updateUiToPremiumVersion();

    protected abstract void updateUiToTrialVersion();
}

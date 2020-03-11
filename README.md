# IQV Android SDK Integration and Configuration

## Requirements
- You will be provided with a parameter set by your account manager. This consists of two string values for appToken and partnerKeyword.

## Install using Gradle

Add PubNative Maven repo to your project level build.gradle file:

```
buildscript {
    repositories {
        // Other dependencies
        maven { url 'https://dl.bintray.com/pubnative/maven' }
    }
    dependencies {
        // ...
    }
}

allprojects {
    repositories {
        // Other dependencies
        maven { url 'https://dl.bintray.com/pubnative/maven' }
    }
}
```

Add IQV SDK dependency to the module level build.gradle file:

```
dependencies {
   implementation 'com.iqv:iqv.adsdk:+'
}
```

## Manifest permissions

To enable the basic features of the IQV SDK, the following permissions must be added in the AndroidManifest.xml file:

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

For improved targeting and therefore higher eCPMs you can add this other permissions but keep in mind that the user needs to approve them explicitly on Android versions 6 or higher.

```
<uses-permission android:name="android.permission.READ_PHONE_STATE" />

<!-- For location use one of the following permissions -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<!-- or -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

## SDK initialisation

On your main Activity or your Application class onCreate method you should initialise the SDK using the app token and partner keyword that were provided to you by your account manager.

```
AdSdk.initialize(
            APP_TOKEN,
            PARTNER_KEYWORD,
            this.application
        ) {
            // Your custom code after AdSdk has been initialised
        }
```

## Advanced configurations

### Proguard
If you are using Proguard in your gradle build, you should add these lines to your proguard file:

```
-keepattributes Signature
-keep class com.iqv.** { *; }
```

### Test mode
During development and testing of the SDK integration it is recommended to enable test mode. This will make the impressions and click not count on the PubNative side. Testing without enabling this mode could result in your account getting blocked because the traffic will be considered fraudulent.

Test mode is disabled by default. To enable test mode, you should use this line in the same location where you initialise the SDK:

```
AdSdk.setTestMode(true)
```

### Targeting parameters
You can add extra information to the requests the SDK makes to the ad server. This can result in higher eCPMs and more accurate ads for the users.

You can set the age, gender and some related keywords that can help improve the audience targeting in the delivered ads.

```
AdSdk.setCoppaEnabled(false)
AdSdk.setAge("30")
AdSdk.setGender("male")
AdSdk.setKeywords("sports,racket,tennis")
```

## IQV Android SDK - Banners and MRECT

### Create XML Layout

Create a BannerAdView inside your layout file

```
<com.iqv.views.BannerAdView
        android:id="@+id/p_banner"
        android:layout_width="320dp"
        android:layout_height="50dp"
        android:layout_gravity="center_horizontal"
        app:layout_constraintBottom_toTopOf="@+id/text_home"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@id/button_load"/>
```

Create an attribute to hold the reference to the UI element.

```
private BannerAdView banner;
```

Get a reference to it from your code.

```
        banner = view.findViewById(R.id.banner)
```

### Requesting and displaying the ad
Use the load method to request an ad using the zone id and setting a listener for the banner.

```
private void loadBanner() {
    mBanner.load(new PNAdView.Listener() {
        @Override
        public void onAdLoaded() {

        }

        @Override
        public void onAdLoadFailed(Throwable error) {

        }

        @Override
        public void onAdImpression() {

        }

        @Override
        public void onAdClick() {

        }
    });
}
```

Once the ad is loaded successfully from the server, the PNBannerAdView will render it and notify when it's ready via the onAdLoaded callback. Any error during fetch or rendering will be received via the onAdLoadFailed callback.
onAdImpression and onAdClick just notify of clicks and impressions. No code needs to be added there. Use those callbacks in case you want to hide the ad after click or some similar use case.
Finally destroy the banner when the activity or fragment are destroyed.

```
override fun onDestroy() {
        if (banner != null) {
            banner.destroy()
        }
        super.onDestroy()
    }
```


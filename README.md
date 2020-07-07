# IQV Android SDK Integration and Configuration

## Requirements
- You will be provided with a parameter set by your account manager. This consists of two string values for appToken and partnerKeyword, and the zone ids that identify each one of your placements.

Default Zone IDs are defined as follows:
2 - Banner Ads
3 - Interstitial Ads
4 - Video Ads

- Your account manager can advise on more customized zone id setup including multiple price floors for the same ad format.

## Install using Gradle dependency

Add PubNative Maven repo to your project level build.gradle file:

``` Groovy
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

``` Groovy
dependencies {
   implementation 'net.pubnative:hybid.sdk:0.11.0-iqv_sdk.2112'
}
```

## Install as a manual dependency

You can download the most recent .aar file of IQV SDK from our bintray [repository](https://dl.bintray.com/pubnative/maven/net/pubnative/hybid.sdk/0.11.0-iqv_sdk.2112/hybid.sdk-0.11.0-iqv_sdk.2112.aar).

Place this file in your project and add it as a dependency.

## Manifest permissions

To enable the basic features of the IQV SDK, the following permissions must be added in the AndroidManifest.xml file:

``` XML
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

For improved targeting and therefore higher eCPMs you can add this other permissions but keep in mind that the user needs to approve them explicitly on Android versions 6 or higher.

``` XML
<uses-permission android:name="android.permission.READ_PHONE_STATE" />

<!-- For location use one of the following permissions -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<!-- or -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

## SDK initialisation

On your main Activity or your Application class onCreate method you should initialise the SDK using the app token and partner keyword that were provided to you by your account manager.

``` Kotlin
HyBid.initialize(
            APP_TOKEN,
            PARTNER_KEYWORD,
            applicationContext) {
            // Your custom code after AdSdk has been initialised
        }
```

## SDK reconfiguration at runtime
Similarly to the initialize function, the reconfigure method can be used to change the SDK settings at runtime

``` Kotlin
HyBid.reconfigure(
            APP_TOKEN,
            PARTNER_KEYWORD,
            applicationContext
        )
```

## Advanced configurations

### Proguard
If you are using Proguard in your gradle build, you should add these lines to your proguard file:

```
-keepattributes Signature
-keep class net.pubnative.** { *; }
-keep class com.vervewireless.advert.** { *; }
```

### Test mode
During development and testing of the SDK integration it is recommended to enable test mode. This will make the impressions and click not count on the PubNative side. Testing without enabling this mode could result in your account getting blocked because the traffic will be considered fraudulent.

Test mode is disabled by default. To enable test mode, you should use this line in the same location where you initialise the SDK:

``` Kotlin
HyBid.setTestMode(true)
```

### Targeting parameters
You can add extra information to the requests the SDK makes to the ad server. This can result in higher eCPMs and more accurate ads for the users.

You can set the age, gender and some related keywords that can help improve the audience targeting in the delivered ads.

``` Kotlin
HyBid.setCoppaEnabled(false)
HyBid.setAge("30")
HyBid.setGender("male")
HyBid.setKeywords("sports,racket,tennis")
```

## IQV Android SDK - Banners and MRECT

### Create XML Layout

**Please note: The class name of the HyBidAdView has changed from previous IQV SDK versions. Please only use net.pubnative.lite.sdk.views.HyBidAdView in your project**

Create a HyBidAdView inside your layout file

``` XML
<net.pubnative.lite.sdk.views.HyBidAdView
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

``` Kotlin
private lateinit var banner: HyBidAdView
```

Get a reference to it from your code.

``` Kotlin
        banner = view.findViewById(R.id.p_banner)
```

### Requesting and displaying the ad
Use the load method to request an ad. You can set a listener for the banner.
Before loading you can define the desired size. Various banner and MRECT formats are supported.

``` Kotlin
private fun loadBanner() {
    // supported sizes are currently 300x250, 320x50, 320x100, 728x90
    banner.setAdSize(AdSize.SIZE_320x50)
    banner.load(ZONE_ID, object : AdView.Listener {
        override fun onAdLoaded() {

        }

        override fun onAdImpression() {

        }

        override fun onAdLoadFailed(error: Throwable?) {

        }

        override fun onAdClick() {

        }
    })
}
```

Once the ad is loaded successfully from the server, the onAdLoaded() function will be called.
Any error during fetch or rendering will be received via the onAdLoadFailed callback.
onAdImpression and onAdClick just notify of clicks and impressions. No code needs to be added there. Use those callbacks in case you want to hide the ad after click or some similar use case.

Now you can show the ad by calling
``` Kotlin
banner.show()
```

Finally destroy the banner when the activity or fragment are destroyed.

``` Kotlin
override fun onDestroy() {
    if (banner != null) {
        banner.destroy()
    }
    super.onDestroy()
}
```

## IQV Android SDK - Interstitials

### Create **HyBidInterstitialAd** attribute in you activity or fragment

``` Kotlin
private lateinit var interstitial : HyBidInterstitialAd;
```

### Request the ad

Create an instance of the interstitial object setting a **listener**. Use the **load** method to make an ad request.

``` Kotlin
private fun loadInterstitial() {
    interstitial = HyBidInterstitialAd(this, ZONE_ID, object : HyBidInterstitialAd.Listener {
        override fun onInterstitialLoaded() {

        }

        override fun onInterstitialLoadFailed(error: Throwable?) {

        }

        override fun onInterstitialImpression() {

        }

        override fun onInterstitialClick() {

        }

        override fun onInterstitialDismissed() {

        }
    })
    interstitial.load()
}
```

After the ad is successfully retrieved from the server, the **onInterstitialLoaded** callback will notify that the ad is ready to be displayed.
You can display the interstitial as soon as it's loaded using after getting the callback. Use the **show** method to display the ad:

``` Kotlin
interstitial.show();
```

if you don't want to show the ad right away after being loaded, you can use the **isReady** method that returns a boolean stating if the ad has been loaded and is ready to be displayed.

``` Kotlin
if (interstitial.isReady()) {
    interstitial.show()
}
```

Finally destroy the interstitial when the activity or fragment are destroyed

``` Kotlin
override fun onDestroy() {
    if (interstitial != null) {
        interstitial.destroy()
    }
    super.onDestroy()
}
```
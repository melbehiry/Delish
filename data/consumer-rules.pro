
# Dagger2
-dontwarn com.google.errorprone.annotations.**

# OkHttp3
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Firebase
-dontwarn retrofit2.Call

# Databinding CardViewBindingAdapter
-dontwarn androidx.cardview.widget.CardView

-keepclassmembers class com.google.samples.apps.iosched.model.** { <fields>; }

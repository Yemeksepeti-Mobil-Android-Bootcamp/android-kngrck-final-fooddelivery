# Foodbasket Application

#### Food delivery application for Kodluyoruz Yemeksepeti Android Bootcamp.

## Used Technologies

* MVVM Architecture
* Retrofit, okHttp
* Dagger Hilt
* Coroutine
* Firebase Authentication
* Lottie Animations
* Firebase Cloud Functions for REST APIs

<div style="display:flex; flex-direction:column; align-items:center;">
<h2 style="margin-bottom:20px; margin-top:20px;">Splash Onboarding and Authentication Screens</h2>
<div style="display:flex; flex-direction:row; align-items:center; margin-vertical:150px;">
    <div style="margin-left:16px">
        <ul>
        <li>Login and sign up is controlled by Firebase Authentication.</li>
        <li>If user successfully login or sign up, access token is saved in Shared Prefences to request authorized APIs.</li>
        <li>In splash screen, if user already logged in, navigates into the application.</li>
        </ul>
    </div>
    <img src="./screenshots/SplashOnBoardingAuth.gif" height="500px" width="250px"/>
</div>
<h2 style="margin-bottom:20px; margin-top:20px; ">Home Screen</h2>
<div style="display:flex; flex-direction:row; align-items:center; margin-vertical:150px;">
    <img src="./screenshots/HomeScreen.gif" height="500px" width="250px"/>
    <div style="margin-left:16px">
        <ul>
        <li>You can show restaurants in Home Screen.</li>
        <li>The restaurants can be searched or filtered by its category.</li>
        </ul>
    </div>
</div>
<h2 style="margin-bottom:20px; margin-top:20px">Restaurant and Meal Details Screens</h2>
<div style="display:flex; flex-direction:row; align-items:center; margin-vertical:150px;">
    <img src="./screenshots/RestaurantDetails.gif" height="500px" width="250px"/>
    <div style="margin-left:16px">
        <ul>
            <li><= You can show restaurant details and its menu in Restaurant Details Screen.</li>
            <li><= You can add the restaurant to favorites by clicking on heart icon.</li>
            <li>You can add the meal to the cart after choose its ingredients and quantity. =></li>
        </ul>
    </div>
    <img src="./screenshots/MealDetails.gif" height="500px" width="250px"/>
</div>
<h2 style="margin-bottom:20px; margin-top:20px">Cart and Favorite Restaurants Screens</h2>
<div style="display:flex; flex-direction:row; align-items:center; margin-vertical:150px;">
    <img src="./screenshots/CartScreen.gif" height="500px" width="250px"/>
    <div style="margin-left:16px">
        <ul>
            <li><= You can set quantities of meals or delete them.</li>
            <li>You can show favorite restaurants and can be deleted by clicking on heart icon. =></li>
        </ul>
    </div>
    <img src="./screenshots/FavoriteScreen.gif" height="500px" width="250px"/>
</div>
<h2 style="margin-bottom:20px; margin-top:20px">Profile Screen</h2>
<div style="display:flex; flex-direction:row; align-items:center; margin-vertical:150px;">
    <div style="margin-left:16px">
        <ul>
            <li>You can show user details and last orders in the Profile Screen.</li>
            <li>You can edit user details or log out from settings.</li>
            <li>There are two types of user.Admin and normal users. Admin user can add restaurant and meals or delete them.</li>
        </ul>
    </div>
    <img src="./screenshots/ProfileScreen.gif" height="500px" width="250px"/>
</div>
</div>
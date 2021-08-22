# Foodbasket Application

## About this project

 Food delivery application for Kodluyoruz Yemeksepeti Android Bootcamp. In this project, I reviewed most used technologies in an Android Project.

## Used Technologies

* MVVM Architecture
* Retrofit, okHttp
* Dagger Hilt
* Coroutine
* LiveData
* Gson
* Lottie Animations
* Firebase Authentication
* Firebase Cloud Functions for REST APIs

## Usage


### Splash Onboarding and Authentication Screens

- Login and sign up is controlled by Firebase Authentication.
- If the user successfully login or sign up, access token is saved in Shared Prefences to request authorized APIs.
- In splash screen, if the user already logged in, navigates into the application.
        
<img src="./screenshots/SplashOnBoardingAuth.gif" height="500px" width="250px"/>

### Home Screen

- You can show restaurants in Home Screen.
- The restaurants can be searched or filtered by its category.

<img src="./screenshots/HomeScreen.gif" height="500px" width="250px"/>
       
### Restaurant Details Screen

- You can show restaurant details and its menu in Restaurant Details Screen.
- You can add the restaurant to favorites by clicking on heart icon

<img src="./screenshots/RestaurantDetails.gif" height="500px" width="250px"/>

### Meal Details Screen
      
- You can add the meal to the cart after choosing its ingredients and quantity.
        
<img src="./screenshots/MealDetails.gif" height="500px" width="250px"/>

### Cart Screen

- You can set quantities of meals or delete them.

<img src="./screenshots/CartScreen.gif" height="500px" width="250px"/>

### Favorite Restaurants Screen

- You can show favorite restaurants and can be deleted by clicking on heart icon.

<img src="./screenshots/FavoriteScreen.gif" height="500px" width="250px"/>

### Profile Screen

- You can show user details and last orders on the Profile Screen.
- You can edit user details or log out from settings.
- There are two types of user. Admin and normal users. Admin users can add restaurant and meals or delete them.
       
<img src="./screenshots/ProfileScreen.gif" height="500px" width="250px"/>

## Acknowledgments

- Kodluyoruz Yemeksepeti Android Bootcamp
- Patika.dev 
- [Erol Kaftanoğlu](https://github.com/erolkaftanoglu)

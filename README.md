![Created](https://img.shields.io/badge/created-july%202018-brightgreen.svg)
[![Contributors](https://img.shields.io/badge/contributors-1-lightgrey.svg)](https://github.com/mmstewart/Server-Diagnostic-Application/graphs/contributors)
[![License](https://img.shields.io/packagist/l/doctrine/orm.svg)](https://github.com/mmstewart/Server-Diagnostic-Application/blob/master/LICENSE.md)
![Updated](https://img.shields.io/badge/updated-july%202018-yellow.svg)

# Server Diagnostic Application

This application diagnoses computer servers, converting JSON files to string.  

# Notice

The application is not complete yet, I will be making updates to the code to make the app is completed. 

This application includes a splash screen (Feel free to add your logo or remove it completely) and a navigation drawer.  

For updates, click [here](UPDATES.md)

## Setup

A step by step series of examples that tell you how to get the application running.

---

First, the main change that you have to make is in the webService() method. To find the webservice() method, it is located at the bottom of the ServerReportScreen class. You are able to request what you need with the request builder.

```diff
+ var request : Request = Request.Builder()
```

Second, you are able to add your url, with the ability to add optional headers, post, get, delete, etc. with the request build.

```diff
var request : Request = Request.Builder()
+        .url("url_here")
+        .header("Authorization", Credentials.basic("a_username","a_password")
+        .cacheControl(CacheControl.FORCE_NETWORK)
```

Last, add the build to the end of your calls and run the program.

```diff
var request : Request = Request.Builder()
        .url("url_here")
        .header("Authorization", Credentials.basic("a_username","a_password")
        .cacheControl(CacheControl.FORCE_NETWORK)
+       .build()
```

**Feel free to add your own calls, as the only two calls you need to run are url and build**

## Screenshots

Screenshots will be updated when there are changes to the design.  

---

![imageedit_2_2117761380](https://user-images.githubusercontent.com/36175538/42836482-c69942ec-89c0-11e8-8822-745c391661ed.png)  

![imageedit_1_5367105670](https://user-images.githubusercontent.com/36175538/42836513-dd365490-89c0-11e8-9779-da377539ee7d.png)  

![imageedit_1_5793646663](https://user-images.githubusercontent.com/36175538/42836515-dfc949ec-89c0-11e8-84b2-fa61eaff2632.png)  

![imageedit_2_6481307018](https://user-images.githubusercontent.com/36175538/42957470-66b2601e-8b48-11e8-86a7-807c4be27587.png)

## Built With

* [OkHttp](http://square.github.io/okhttp/) - An HTTP & HTTP/2 client for Android and Java

## Author

* **Marcus Stewart** - *Initial work* - [MMStewart](https://github.com/mmstewart)

## Acknowledgments

* [Equus](https://www.equuscs.com/)
* [Navigation Drawer](https://www.youtube.com/watch?v=AS92bq3XxkA)
* [Splash Screen](https://www.youtube.com/watch?v=jXtof6OUtcE&t=137s)
* [Shields](https://shields.io/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

# Server Diagnostic Application

This application diagnoses computer servers, converting JSON files to string.  

This application includes a splash screen (Feel free to add your logo or remove it completely) and a navigation drawer.

# Notice

The application is not complete yet, I will be making updates to the code to make the app is completed.

## Setup

A step by step series of examples that tell you how to get the application running.

---

First, the main change that you have to make is in the webService() method. To find the webservice() method, it is located at the bottom of the ServerReportScreen class. You are able to request what you need with the request builder.

```
var request : Request = Request.Builder()
```

Second, you are able to add your url, with the ability to add optional headers, post, get, delete, etc. with the request build.

```
var request : Request = Request.Builder()
        .url("url_here")
        .header("Authorization", Credentials.basic("a_username","a_password")
        .cacheControl(CacheControl.FORCE_NETWORK)
```

Last, add the build to the end of your calls and run the program.

```
var request : Request = Request.Builder()
        .url("url_here")
        .header("Authorization", Credentials.basic("a_username","a_password")
        .cacheControl(CacheControl.FORCE_NETWORK)
        .build()
```

**Feel free to add your own calls, as the only two calls you need to run are url and build**

## Screenshots

Screenshots will be updated when there are changes to the design.  

---

![imageedit_2_2117761380](https://user-images.githubusercontent.com/36175538/42836482-c69942ec-89c0-11e8-8822-745c391661ed.png)  

![imageedit_1_5367105670](https://user-images.githubusercontent.com/36175538/42836513-dd365490-89c0-11e8-9779-da377539ee7d.png)  

![imageedit_1_5793646663](https://user-images.githubusercontent.com/36175538/42836515-dfc949ec-89c0-11e8-84b2-fa61eaff2632.png)  

![imageedit_1_5585713203](https://user-images.githubusercontent.com/36175538/42836520-e253c066-89c0-11e8-9518-344bb70efa36.png)

## Built With

* [OkHttp](http://square.github.io/okhttp/) - An HTTP & HTTP/2 client for Android and Java

## Author

* **Marcus Stewart** - *Initial work* - [MMStewart](https://github.com/mmstewart)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* [Equus](https://www.equuscs.com/)
* [Navigation Drawer](https://www.youtube.com/watch?v=AS92bq3XxkA)
* [Splash Screen](https://www.youtube.com/watch?v=jXtof6OUtcE&t=137s)

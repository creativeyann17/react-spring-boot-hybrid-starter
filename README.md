# react-spring-boot-hybrid-starter

Based on [the amazing work of Eirik Sletteberg](https://github.com/eirslett/frontend-maven-plugin)

You can find a working demo [here](https://creativeyann-hybrid.herokuapp.com/)

Current FrontEnd behavior is:
* login as GUEST (API required authentification for any /api/** endpoints)
* GET API version at /api/version
* open websocket at /api/websocket
* receive the online users count from API via websocket
* after 60 seconds logout (auto close websocket)

This custom starter contains the following:
* react 16
  * router/intl/redux/saga/logger/axios
  * service worker (disabled by default)
  * one default layout and page
  * REST login/logout with CSRF support
  * Javascript WebSocket managed by saga
  * some tests
* spring Boot 2
  * lombok/security/cors/logger
  * REST login/logout with CSRF support
  * WebSocket with JSON encoder/decoder
  * swagger-ui
  * custom configuration
  * 'dev' ready profil with H2
  * some tests

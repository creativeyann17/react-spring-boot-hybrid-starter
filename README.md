# react-spring-boot-hybrid-starter

Based on [the amazing work of Eirik Sletteberg](https://github.com/eirslett/frontend-maven-plugin)

You can find a working demo [here](https://creativeyann-hybrid.herokuapp.com/)

This custom starter contains the following:
* react 16
  * router/intl/redux/saga/logger/axios
  * service worker (disabled by default)
  * one default layout and page
  * REST login/logout with CSRF support
  * login as GUEST then fetch API version then logout at startup
  * some tests
* spring Boot 2
  * lombok/security/cors/logger
  * REST login/logout with CSRF support
  * swagger-ui
  * custom configuration
  * 'dev' ready profil with H2
  * some tests

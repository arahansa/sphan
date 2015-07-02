#Beginning
Hello, My name is Choi kangyong. 

My nick name is arahansa. 
This program is intended for Coding test. 
and this document is written in english. 
If you want to read documentation in __Korean__, `open  doc_apidescription(pdf or ppsx) file`. 

#Install

For starters, You have to install [Java8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), [gradle](http://gradle.org)

and then, just type `gradle bootRun` in your command shell.

API description
========

This program has those APIS(type: application/json)
###1. localhost:8080/article (type: GET) 
you can __SEE ALL ARTICLE LIST__

![list image](https://lh3.googleusercontent.com/Y6ZnJeFyKppOlhFfi1MG-CYywtYzsfwHyGmFxE7M4dc=w396-h275-no)

###2. localhost:8080/article/(article id) (type:GET)
you can __SEE ONE ARTICLE__. if article isn't exist, you'll see 404 code

![get article](https://lh3.googleusercontent.com/4XidF8RNnGXB5tC84ejQDEB5_lJBNsM9z8VeyuM3cKA=w380-h193-no)


![error page](https://lh3.googleusercontent.com/MBGwoEFct1jZ6DveAOFeCRfh9MH2t4IDd47UIPluOFY=w284-h128-no)

###3. localhost:8080/article (type:POST)
__WRITE ARTICLE__. if you doesn't fill field such as nick or subject, you'll see a kind message. 
``` expected error : if your json format is invalid, you will see other format response ```

![post](https://lh3.googleusercontent.com/Tch3PZqDpbZ4qGjrr60lxTcv7cDoGwISxdJRbqAROgA=w381-h195-no)


![post2](https://lh3.googleusercontent.com/mWY6d3XwEsD8geALR1rt-IBJeaqpo90m1sZXGvVzgj8=w355-h192-no)

###4. localhost:8080/article/(article id) (type:PUT)
__UPDATE ARTICLE__

![update](https://lh3.googleusercontent.com/uF_Eu9kph8L6qjLNa2Y2_jYpyQUb5G7pY6KDCWKwqpQ=w337-h194-no)


###5. localhost:8080/article/(article id) (type:DELETE)
__DELETE ARTICLE__

![delete](https://lh3.googleusercontent.com/ePLO1grYRdmdAB6KgtmXLiF_9j2Uk0hX1p2M3d8xfHk=w369-h269-no)




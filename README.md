# XCS-CF Competition System

> Made by isSJa

## backgroud

![notice](./doc-img/notice.jpg)

## purpose

This project is to help the admin to manage the students who belongs to XCS and their points of contests in CF,as well to make it convenient for members of XCS to check the details of their points.

## technology stack

- BE：
  - springBoot
  - Mybatis-Plus
  - mysql
  - jwt
- FE：
  - html、css、js
  - vue3
  - vue-router
  - sass
  - typescript
  - axios
  - element-plus

## function

* register

* login

* for user
  
  * the information of members of XCS
    * check the details of points of them
    * search message through id , name , student number or CF account 
    * view the overall situation of CF contests in the form of point chart
  * the information of contests in CF
    * view the specific information of each contest, which includes 2 kinds of points, "duringContest points" and "afterContest points", and the members who have obtained these points
  * view point rules
  * personal center
    * display personal information
    * change avatar, username, email or password

* for admin
  
  * add some data
    * add a contest in CF
    * add a menmber of XCS
    * add the points a member gained in a CF contest
  * operate the members of XCS
    * check the details of points of them
    * search message through id , name , student number or CF account
    * view the overall situation of CF contests in the form of point chart
    * modify the information of them
    * delete a member
  * operate the contests in CF
    * view the specific information of each contest, which includes 2 kinds of points, "duringContest points" and "afterContest points", and the members who have obtained these points
    * modify the information of them
    * delete a contest
  * view point rules
  * personal center
    * display personal information
    * change avatar, username, email or password

* logout 

## preview

#### the interface of user

![user-index](./doc-img/user-index.jpg)

![user-index2](./doc-img/user-index2.jpg)

![user-student](./doc-img/user-student.jpg)

![user-student-sort](./doc-img/user-student-sort.jpg)

![user-student-detai](./doc-img/user-student-detai.jpg)

![user-student-search](./doc-img/user-student-search.jpg)

![user-student-overall](./doc-img/user-student-overall.jpg)

![user-contest](./doc-img/user-contest.jpg)

![user-contest-detail](./doc-img/user-contest-detail.jpg)

![user-rules](./doc-img/user-rules.jpg)

![user-personal](./doc-img/user-personal.jpg)

#### the interface of admin

![admin-index](./doc-img/admin-index.jpg)

![admin-add1](./doc-img/admin-add1.jpg)

![admin-add2](./doc-img/admin-add2.jpg)

![admin-student](./doc-img/admin-student.jpg)

![admin-student-edit](./doc-img/admin-student-edit.jpg)

![admin-student-delete](./doc-img/admin-student-delete.jpg)

![admin-contest](./doc-img/admin-contest.jpg)

![admin-contest-detail](./doc-img/admin-contest-detail.jpg)

![admin-contest-edit](./doc-img/admin-contest-edit.jpg)

![admin-contest-delete](./doc-img/admin-contest-delete.jpg)

![admin-personal](./doc-img/admin-personal.jpg)

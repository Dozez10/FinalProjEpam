<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="/WEB-INF/ResourceBundleUTF.tld"%>
<%@taglib prefix="log" uri="http://logging.apache.org/log4j/tld/log"%>
<log:setLogger logger="indexJsp" var="indexJsp"/>
<log:log level="info" logger="${indexJsp}" message="Index jsp has been visited"/>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang :pageContext.request.locale.language}" scope="session" />
<fmt:setLocale name="${lang}" />
<fmt:setBundle basename="resources" />
<html>
<head>
    <title>seeOrders</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet" type="text/css">
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet" type="text/css">
    <link href="https://maxst.icons8.com/vue-static/landings/line-awesome/font-awesome-line-awesome/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/animate.css@^4.0.0/animate.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.min.css" rel="stylesheet" type="text/css">

</head>
<style>


    .ordersList{

        position: absolute;
        width: 100%;
        height: 81%;
        text-align: center;

        box-sizing: border-box;
    }
    .orderItem{

        position: relative;
        width: 100%;
        height: 28%;
        margin-top: 1%;
        border-top:4px solid blue;
        box-sizing: border-box;
    }


    .orderItem div {

        position: relative;
        margin-left: 1%;

    }
    .linksForOrderItem{
        float: left;
        height: 100%;
        top:5%;
        border: 4px solid blue;
        background-color: yellow;
        border-radius: 20%;

    }
    .classToAllTheLinks{
        display: block;
        position: relative;
        text-decoration: none;
        margin-top: 7%;
        color:blue;
        border-bottom: 1px solid blue;
        text-align: center;
        font-size: large;
        font-weight: bold;
        border-radius: 10%;
    }
    .isApplied{
        float: left;
        top:25%;
        height: 70%;
        width: 10%;
        border:4px solid blue ;
        background-color: yellow;
        border-radius: 20%;
    }
    .isApplied span{
        display: block;
        position: relative;
        color:blue;
        font-weight: bold;
        text-align: center;
    }
    .isAppliedLable{
        font-size: large;
        border-radius: 10%;
        border-bottom: 1px solid blue;
        margin-top: 1%;
    }
    .isAppliedValue{
        top:2%;
        font-size:xx-large;
    }
    .serviceType{
        float: left;
        top:15%;
        height: 70%;
        width: 15%;
        border:4px solid blue ;
        background-color: yellow;
        border-radius: 20%;
    }
    .serviceType span {
        display: block;
        position: relative;
        color:blue;
        font-weight: bold;
        text-align: center;

    }
    .serviceTypeLable{
        font-size: large;
        border-radius: 10%;
        border-bottom: 1px solid blue;
        margin-top: 1%;
    }
    .serviceTypeValue{
        top:2%;
        font-size: x-large;
    }
    .timeSlot{
        float: left;
        top:15%;
        height: 70%;
        width: 15%;
        border:4px solid blue ;
        background-color: yellow;
        border-radius: 20%;
    }
    .timeSlot div {
        position: relative;
        text-align: center;
        color:blue;
    }
    .startTime{
        text-align: center;
        height: 48%;
        width: 100%;
        color:blue;
        border-bottom: 1px solid blue;


    }
    .startTime span {
        display: block;
        position: relative;
        text-align: center;
        color:blue;
        font-weight: bold;
    }

    .startTimeLable{
        font-size: large;
    }
    .startTimeValue{
        top:4%;
    }

    .endTime{
        text-align: center;
        color:blue;
        height: 48%;
        width: 100%;

    }

    .endTime span{
        display: block;
        position: relative;
        text-align: center;
        color:blue;
        font-weight: bold;
    }
    .endTimeLable{
        font-size: large;
    }
    .endTimeValue{
        top:4%;
    }
    .masterName{
        float: left;
        top:10%;
        height: 80%;
        width: 12%;
        background-color: yellow;
        border:4px solid blue;
        border-radius: 20%;
    }
    .masterName span{
        display: block;
        position: relative;
        color:blue;
        font-weight: bold;
        text-align: center;

    }
    .masterNameLable{
        border-bottom: 1px solid blue;
        font-size: larger;
    }
    .masterNameValue{
        top:10%;
    }
    .userLogin{
        float: left;
        top:10%;
        height: 80%;
        width: 12%;
        background-color: yellow;
        border:4px solid blue;
        border-radius: 20%;
    }
    .userLogin span{
        display: block;
        position: relative;
        color:blue;
        font-weight: bold;
        text-align: center;
    }
    .userLoginLable{
        border-bottom: 1px solid blue;
        font-size: larger;
    }
    .userLoginValue{
        top:10%;
    }
    .orderId{
        float: right;
        top:70%;
        right: 2%;
        color:black;
        font-weight: bold;
        font-size: medium;
        border-left: 4px solid yellow;
    }
    .orderId span{

        font-weight: bold;
        font-size:larger;
        font-style: italic;

    }
    .titleOfOrdersList{
        color:blue;
        font-size: x-large;
        font-weight: bold;
    }
    .classToAllTheLinks:hover{
        background-color: rgb(17, 0, 255);
        color: yellow;
    }
    .orderItem:nth-of-type(2){
       margin-top: 0;
    }
    /*.buttonArrows{*/

    /*}*/
    #pageNumberHolder{
        width: 40px;
    }
</style>



<body>
<script src="https://cdn.jsdelivr.net/npm/vue@^2.0.0/dist/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.umd.modern.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<div id="q-app">
    <q-toolbar  class="bg-yellow-14 text-white shadow-2 q-pa-md">
        <span class="q-ml-md text-h5"><fmt:localeValue key="headerCompanyName"/></span>
        <q-btn size="md" @click="goHome()" class="q-mx-md" label="<fmt:localeValue key="headerHomePage"/>"></q-btn>
        <q-space></q-space>
        <c:if test="${sessionScope.Login==null}">
            <q-btn label="<fmt:localeValue key="singInHeader"/>"  size="md" @click="() => { showDialogD = true }"></q-btn>
            <div>
                <q-dialog v-model="showDialogD" no-backdrop-dismiss>
                    <div>
                        <q-form
                                action="${pageContext.request.contextPath}/pages/guest/login" method="post"
                        >
                            <q-card style="width: 400px;">
                                <q-toolbar class="bg-yellow-14 text-black text-center">
                                    <q-toolbar-title>
                                        <fmt:localeValue key="singInHeader"/>
                                    </q-toolbar-title>
                                    <q-btn flat round color="black" icon="close" v-close-popup></q-btn>
                                </q-toolbar>
                                <q-card-section class="inset-shadow"/>
                                <q-input
                                        filled
                                        debounce="300"
                                        type="text"
                                        :hint="LogHintD"
                                        v-model="loginD"
                                        label="<fmt:localeValue key="dialogLogin"/>"
                                        name="login"
                                        class="q-ma-xs"
                                        :rules="[ val => val.match(/(^([a-z-а-яА-ЯЇЄЁЭїєёэ]{5,19})$)/ig)!=null || '<fmt:localeValue key="loginValidation" />']"
                                >
                                    <template v-slot:before>
                                        <q-icon name="login"></q-icon>
                                    </template>
                                </q-input>
                                <q-input v-model="passwordD"
                                         class="q-ma-xs"
                                         debounce="300"
                                         name="psw"
                                         label="<fmt:localeValue key="dialogPassword"/>"
                                         :rules="[ val => val.match(/(^([\wа-яА-ЯЇЄЁЭїєёэ]{5,20})$)/ig)!=null || '<fmt:localeValue key="passwordValidation"/>']"
                                         filled :type="isPwdD ? 'password' : 'text'"
                                         :hint="PasswordHintD">
                                    <template v-slot:before>
                                        <q-icon
                                                :name="isPwdD ? 'visibility_off' : 'visibility'"
                                                class="cursor-pointer"
                                                @click="isPwdD = !isPwdD"
                                        ></q-icon>
                                    </template>
                                </q-input>
                                <q-btn class="text-black" style="width:100%;" label="<fmt:localeValue key="dialogLogInToAccount"/>" type="submit" color="yellow-14"/>

                            </q-card>
                        </q-form>
                    </div>
                </q-dialog>
            </div>
            <q-btn

                    label="<fmt:localeValue key="headerSignUp"/>"
                    push
                    @click="RegistrationPage()"
                    col="col-6"
                    size="md"
                    v-close-popup
            ></q-btn>
        </c:if>
        <c:if test="${sessionScope.Login!=null}">
            <q-btn-dropdown size="md" icon="perm_identity" stretch flat label="<fmt:localeValue key="headerAccountSettings"/>" >
                <div class="column justify-center">
                    <div class="text-h6"><fmt:localeValue key="indexSettings"/></div>
                    <q-btn  color="primary" @click="profilePage()" stretch flat label="<fmt:localeValue key="loggedProfile"/>"></q-btn>
                </div>

                <q-separator vertical inset class="q-mx-lg"></q-separator>
                <div class="column items-center">
                    <q-avatar size="72px" icon="portrait"></q-avatar>
                    <div class="text-subtitle1 q-mt-md q-mb-xs">${sessionScope.Login}</div>
                    <div class="text-subtitle1 q-mt-md q-mb-xs"> ${sessionScope.userType}</div>
                    <q-form
                            action="${pageContext.request.contextPath}/pages/user/logOut" method="post"
                    >
                        <q-btn

                                label="<fmt:localeValue key="loggedLogout"/>"
                                push
                                size="md"
                                v-close-popup
                                type="submit"
                        ></q-btn>
                    </q-form>
                </div>
            </q-btn-dropdown>
        </c:if>
        <q-separator dark vertical></q-separator>
        <q-btn size="md" :color="Eng" @click="ChangeToEng()"  label="EN"></q-btn>
        <q-btn size="md" :color="Ua" @click="ChangeToUa()"  label="UA"></q-btn>
        <q-btn size="md" :color="Ru" @click="ChangeToRu()"  label="RU"></q-btn>


    </q-toolbar>

</div>




<div class="ordersList"  >
    <div class="titleOfOrdersList"  >
        <fmt:localeValue key="listOfOrders" />
    </div>
    <c:forEach var="orderItem" items="${requestScope.listOfOrders}" varStatus="loop" >
        <div class="orderItem" >
            <div class="orderId"><span>order id:</span> ${orderItem.orderId}</div>
            <div class="userLogin"><span class="userLoginLable" ><fmt:localeValue key="userLogin" /></span><span class="userLoginValue">${requestScope.userListT.get(loop.index).userLogin}</span></div>
            <div class="masterName"><span class="masterNameLable"><fmt:localeValue key="masterName" /></span><span class="masterNameValue">${requestScope.mastersT.get(loop.index).masterName}</span></div>
            <div class="timeSlot">
                <div class="startTime"><span class="startTimeLable"><fmt:localeValue key="startTime" /></span><span class="startTimeValue">${orderItem.startTime.toLocalDate()} ${orderItem.startTime.toLocalTime()} </span></div>
                <div class="endTime"><span class="endTimeLable"><fmt:localeValue key="endTime" /></span><span class="endTimeValue">${orderItem.endTime.toLocalDate()} ${orderItem.endTime.toLocalTime()}</span></div>
            </div>
            <div class="serviceType"><span class="serviceTypeLable"><fmt:localeValue key="serviceType" /></span><span class="serviceTypeValue">${requestScope.servicesListTo.get(loop.index).serviceType}</span></div>
            <div class="isApplied"><span class="isAppliedLable"><fmt:localeValue key="appliedStatus" /></span><span class="isAppliedValue">${orderItem.applied}</span></div>
            <div class="linksForOrderItem" >
                <a href="${pageContext.request.contextPath}/pages/admin/removeOrder?orderId=${orderItem.orderId}" class="classToAllTheLinks removeLinkOrderItem"><fmt:localeValue key="removeOrder" /></a>
                <a href="${pageContext.request.contextPath}/pages/admin/applyOrder?orderId=${orderItem.orderId}" class="classToAllTheLinks confirmLinkOrderItem"><fmt:localeValue key="confirmOrder" /></a>
                <a href="${pageContext.request.contextPath}/pages/admin/changeOrder?orderId=${orderItem.orderId}&masterId=${requestScope.mastersT.get(loop.index).masterId}&pageNumber=1" class="classToAllTheLinks changeLinkOrderItem"><fmt:localeValue key="changeTimeSlot" /></a>
            </div>
        </div>
    </c:forEach>
<button  id="leftArrowPage" class="buttonArrows"><</button>
    <input disabled="disabled" id="pageNumberHolder" type="text">
  <button id="RightArrowPage" class="buttonArrows">&gt;</button>
</div>

</body>
<script>

    const dataTable = parseInt('${Count}',10);
    new Vue({
        el: '#q-app',
        data () {
            return {
                passwordD: '',
                loginD:'',
                isPwdD: true,
                LogHintD:'<fmt:localeValue key="LogHintD" />',
                PasswordHintD:'<fmt:localeValue key="PasswordHintD" />',
                showDialogD: false,
                Eng:'',
                Ua:'',
                Ru:''

            }
        },
        mounted: function(){
            window.history.pushState({}, document.title,window.location.href.split(/[?#]/)[0]);
            if('${param.errorMessage}'!==''){
                this.$q.notify({
                    type: 'negative',
                    message: '${param.errorMessage}',
                    position:'center',
                    icon: 'report_problem'
                })
            }

            switch ('${sessionScope.lang}'){
                case 'en':{
                    this.Eng='black'
                    break
                }
                case 'ua':
                case 'uk_UA':{
                    this.Ua='black'
                    break
                }
                case 'ru':{
                    this.Ru='black'
                    break
                }
            }
        },

        methods: {

            ChangeToEng(){
              window.location.href = window.location.href+'?lang=en';
            },
            ChangeToUa(){
                window.location.href = window.location.href+'?lang=ua';
            },
            ChangeToRu(){
                window.location.href = window.location.href+'?lang=ru';
            },
            goHome(){
                window.location.replace('${pageContext.request.contextPath}'+'/pages/index?lang=${sessionScope.lang}');
            }
        }
    })

var count;
var pageNumber;
var pageNumberHolder;
var arrowMoveLeft;
var arrowMoveRight;

    window.addEventListener('load',()=>{
        count = ${requestScope.countPages}
        pageNumber = ${requestScope.pageNumberss}
     arrowMoveLeft = document.getElementById('leftArrowPage');
     pageNumberHolder = document.getElementById('pageNumberHolder');
     arrowMoveRight = document.getElementById('RightArrowPage');
   pageNumberHolder.value = pageNumber;

   if(pageNumber>=count)arrowMoveRight.disabled=true;
   if(pageNumber<=1)arrowMoveLeft.disabled=true;
  arrowMoveLeft.addEventListener('click',()=>{
      pageNumber--;
      window.location.href = '${pageContext.request.contextPath}/pages/admin/records?pageNumber='+pageNumber+'&lang=${sessionScope.lang}';
        })
arrowMoveRight.addEventListener('click',()=>{
    pageNumber++;
    window.location.href = '${pageContext.request.contextPath}/pages/admin/records?pageNumber='+pageNumber+'&lang=${sessionScope.lang}';
})
    });




</script>
</html>

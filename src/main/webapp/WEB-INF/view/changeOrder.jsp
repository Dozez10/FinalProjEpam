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
    .TimeLine{

        height: 15px;
        background-color: rgb(241, 171, 19);
        position: absolute;
        top:40%;
        border-radius: 4px;

    }

    .InnerTimeLine{

        height: 15px;
        background-color: rgb(255, 242, 0);
        position:absolute;



    }
    .CirclePick{
        position:absolute;
        background-color: rgb(224, 113, 76);
        width: 20px;
        height: 21px;
        border-radius: 10px;
        top:-3px;

    }
    .CirclePickLeft{

        left:-5px;

    }
    .CirclePickRight{

        right:-5px;

    }

    .InnerCirclePick

    {

        position:absolute;
        background-color: rgb(255, 255, 255);
        width: 10px;
        height: 10.5px;
        border-radius: 5px;
        top:5.25px;
        left:5px;
    }

    .tipForDisplayingTime{

        position: absolute;
        text-decoration: underline;
        text-decoration-color: rgb(241, 171, 19);
        color:black;
        font-weight: bold;
        display: block ;
    }
    #tipForDisplayingTimeRight{
        top:150%;
    }
    #tipForDisplayingTimeLeft{
        top:-150%;
    }
    #timeLineLabelT{
        position:absolute;
        top:-200%;
        left:-9.5%;
        color:black;
        font-size: medium;
        font-weight: bold;
        text-decoration: underline;
        text-decoration-color: rgb(241, 171, 19) ;


    }


    label{
        color:rgb(241, 171, 19);
        border-bottom-style: groove;
        border-bottom-color: rgb(241, 171, 19);
        background-color: white;
    }
    #dayChooseLabel{
        position: absolute;
        top:23%;
        left:7%;
    }
    #dayChoosing{
        position: absolute;
        top:28%;
        left:7%;
    }
    #startTimeTo{
        position: absolute;
        top:38%;
        left:1%;
    }
    #startAndEndTimeLabel{
        position: absolute;
        top:32%;
        left:1%;
    }
    #endTimeTo{
        position: absolute;
        top:38%;
        left:10%;
    }
    #startAndEndTimeOfDayAvailable{
        position: absolute;
        top:15%;
        left:30%;
        border: 4px solid rgb(241, 171, 19);
        border-radius: 20px;
        text-align: center;
    }
    .timeSlotsDay{
        position: relative;
        float: left;
        text-align: center;
        border-right:2px solid rgb(241, 171, 19);

    }
    .timeSlotsDay:last-child{
        border-right:none;
    }
    .titleTimeSlots{
        border-bottom: 1px solid rgb(241, 171, 19);
        font-weight: bold;
        font-size: larger;
    }
    .mainTitleTimelots{
        font-weight: bold;
        font-size: large;
    }
    .timeSlotOfADay{
        font-size: medium;
    }
    #makeOrderButton{
        position: absolute;
        left:5%;
        top:44%;
        color:rgb(241, 171, 19);

    }
    #pageNumberHolder{
        width: 40px;
    }
    .sliderArrows{
        position: absolute;
        bottom: 5%;
        left:50%;
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

<label id = "dayChooseLabel" for="dayChoosing"><fmt:localeValue key="DayChoosing"/></label>
<select id="dayChoosing" >
    <c:forEach var="dateObject" items="${requestScope.dateOfTimeLine}">
        <option value="SomeDay">${dateObject}</option>
    </c:forEach>
</select>

<label id="startAndEndTimeLabel" for="startTimeTo"><fmt:localeValue key="startTimeToAndEndTime"/></label>
<input type="time" class="timeInputs" id="startTimeTo" >

<input type="time" class="timeInputs" id="endTimeTo" >

<div id="startAndEndTimeOfDayAvailable" class="timeSlotsAvailable" >
    <span class="mainTitleTimelots" ><fmt:localeValue key="TimeSlotsAvailable"/></span>
    <br>
    <c:forEach var="dateOf" items="${requestScope.dateOfTimeLine}" varStatus="loop" >
        <div class="timeSlotsDay"  >
            <span class="titleTimeSlots">${dateOf}</span><br>
            <c:forEach var="timeSlotCurrent" items="${requestScope.timeSlotsAvailableList.get(loop.index)}" varStatus="loop" >
                <span class="timeSlotOfADay">${loop.index+1}&nbsp;|&nbsp;${timeSlotCurrent.startTime.toLocalTime()}&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;${timeSlotCurrent.endTime.toLocalTime()}&nbsp;&nbsp;&nbsp;</span><br>
            </c:forEach>
        </div>
    </c:forEach>
</div>

<form id="makeOrderForm"  method="post"></form>
<button form="makeOrderForm" id="makeOrderButton"><fmt:localeValue key="changeTimeSlot"/></button>
<div class="sliderArrows" >
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
                window.location.href = window.location.href+'?lang=en&orderId=${param.orderId}&masterId=${param.masterId}&pageNumber=1';
            },
            ChangeToUa(){
                window.location.href = window.location.href+'?lang=ua&orderId=${param.orderId}&masterId=${param.masterId}&pageNumber=1';
            },
            ChangeToRu(){
                window.location.href = window.location.href+'?lang=ru&orderId=${param.orderId}&masterId=${param.masterId}&pageNumber=1';
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
    var dayChoose;
    var pixelForMinutes = 2;
    var arrayOfStartTimes = [];
    var arrayOfEndTimes = [];
    var makeOrderButton;
    window.addEventListener('load',()=>{
        <c:set var = "topOffsetTimeLineOffset" scope="request" value = "45"/>
        <c:forEach var="startTime" items="${requestScope.startTimeSlots}" varStatus="loop" >
        arrayOfStartTimes[${loop.index}] = ${startTime}
        arrayOfEndTimes[${loop.index}] = ${requestScope.endTimeSlots.get(loop.index)}
         createTimeLine(arrayOfStartTimes[${loop.index}],arrayOfEndTimes[${loop.index}],${topOffsetTimeLineOffset},'${requestScope.dateOfTimeLine.get(loop.index).toString()}');
        <c:set var="topOffsetTimeLineOffset" scope="request" value="${topOffsetTimeLineOffset+10}"/>
        </c:forEach>
        <c:remove var="topOffsetTimeLineOffset" scope="request"/>
        dayChoose = document.getElementById("dayChoosing");
        makeOrderButton = document.getElementById('makeOrderButton');
        makeOrderButton.addEventListener('click',(event)=>{
            let startTime = document.getElementById('startTimeTo').value;
            let endTime = document.getElementById('endTimeTo').value;
            let dayDateValue = dayChoose.options[dayChoose.selectedIndex].text;
            event.target.formAction = '${pageContext.request.contextPath}/pages/admin/pChangeOrder?lang=${sessionScope.lang}&masterId=${param.masterId}&orderId=${param.orderId}&startTime='+startTime+'&endTime='+endTime+'&dayDate='+dayDateValue;

        })


           count = ${requestScope.countPages}
            pageNumber = ${requestScope.pageNumber}
                arrowMoveLeft = document.getElementById('leftArrowPage');
        pageNumberHolder = document.getElementById('pageNumberHolder');
        arrowMoveRight = document.getElementById('RightArrowPage');
        pageNumberHolder.value = pageNumber;
        if(pageNumber>=count)arrowMoveRight.disabled=true;
        if(pageNumber<=1)arrowMoveLeft.disabled=true;
        arrowMoveLeft.addEventListener('click',()=>{
            pageNumber--;
            window.location.href = '${pageContext.request.contextPath}/pages/admin/changeOrder?pageNumber='+pageNumber+'&lang=${sessionScope.lang}&masterId=${param.masterId}&orderId=${param.orderId}';
        })
        arrowMoveRight.addEventListener('click',()=>{
            pageNumber++;
            window.location.href = '${pageContext.request.contextPath}/pages/admin/changeOrder?pageNumber='+pageNumber+'&lang=${sessionScope.lang}&masterId=${param.masterId}&orderId=${param.orderId}';
        })



    });

    function createTimeLine(arrayOfStartTimeValues,arrOfEndTimeValues,topValue,dateOf){
        let timeLine = document.createElement('div');
        let timeLineStyle = timeLine.style;
        timeLine.id = 'TimeLineID';
        timeLine.className = 'TimeLine';
        timeLine.style.top = topValue+"%";
        let futureWidth = (arrOfEndTimeValues[0] - arrayOfStartTimeValues[0])*pixelForMinutes;
        let toMoveLeft  = window.screen.width*0.35;

        if(futureWidth>0.6*window.screen.width){
            futureWidth/=pixelForMinutes;
            pixelForMinutes = 0.6*window.screen.width/futureWidth;
            futureWidth*=pixelForMinutes;
        }
        timeLineStyle.left = toMoveLeft+"px";

        timeLineStyle.width = futureWidth+"px";
        let dateOfTheTimeLine = document.createElement('div');
        dateOfTheTimeLine.id = "timeLineLabelT";
        dateOfTheTimeLine.innerText = dateOf;
        timeLine.appendChild(dateOfTheTimeLine);
        document.body.appendChild(timeLine);

        if(arrayOfStartTimeValues.length>0)
        {

            for(let i=1;i<arrOfEndTimeValues.length;i++){//now building time limits to show on the timeline

                let innerTimeLine = document.createElement('div');
                let circlePickLeft = document.createElement('div');
                let circlePickRight = document.createElement('div');
                let innerCirclePickLeft = document.createElement('div');
                let innerCirclePickRight = document.createElement('div');
                let innerTimeLineStyle = innerTimeLine.style;
                let flyingTip = document.createElement('div');
                let flyingTip2 = document.createElement('div');
                flyingTip.id = "tipForDisplayingTimeRight";
                flyingTip2.id = "tipForDisplayingTimeLeft";
                flyingTip.className = "tipForDisplayingTime";
                flyingTip2.className = "tipForDisplayingTime";
                innerTimeLine.className = "InnerTimeLine";
                innerTimeLine.id = "InnerTimeLine "+i;
                circlePickLeft.className = "CirclePick CirclePickLeft";
                circlePickLeft.id = "CirclePickLeft "+i;
                circlePickRight.className = "CirclePick CirclePickRight"
                circlePickRight.id = "CirclePickRight "+i;
                innerCirclePickLeft.className = "InnerCirclePick";
                innerCirclePickLeft.id = "InnerCirclePickLeft "+i;
                innerCirclePickRight.className = "InnerCirclePick";
                innerCirclePickRight.id = "InnerCirclePickRight "+i;
                flyingTip.innerHTML =  Math.trunc(arrayOfStartTimeValues[i]/60)+":"+arrayOfStartTimeValues[i]%60;
                flyingTip2.innerHTML = Math.trunc(arrOfEndTimeValues[i]/60)+":"+arrOfEndTimeValues[i]%60;
                let leftTimeLimit = arrayOfStartTimeValues[i];
                let rightTimeLimit = arrOfEndTimeValues[i];
                let futureWidth = (rightTimeLimit - leftTimeLimit)*pixelForMinutes;
                let moveToLeft = (leftTimeLimit - arrayOfStartTimeValues[0])*pixelForMinutes;
                innerTimeLineStyle.width = futureWidth+"px";
                innerTimeLineStyle.left = moveToLeft+"px";
                //append all the elements
                circlePickLeft.appendChild(flyingTip);
                circlePickRight.appendChild(flyingTip2);
                circlePickLeft.appendChild(innerCirclePickLeft);
                circlePickRight.appendChild(innerCirclePickRight);
                innerTimeLine.appendChild(circlePickLeft);
                innerTimeLine.appendChild(circlePickRight);
                timeLine.appendChild(innerTimeLine);
                //add event listeners to elements

            }


        }

    }


</script>
</html>

<%@ taglib prefix="fmt" uri="/WEB-INF/ResourceBundleUTF.tld"%>
<%@taglib prefix="log" uri="http://logging.apache.org/log4j/tld/log"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<log:setLogger logger="registrJsp" var="registrJsp"/>
<log:log level="info" logger="${registrJsp}" message="Registration jsp has been visited"/>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang :pageContext.request.locale}" scope="session" />
<fmt:setLocale name="${lang}" />
<fmt:setBundle basename="resources" />
<html>
<head>
<title>makeOrder</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet" type="text/css">
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet" type="text/css">
    <link href="https://maxst.icons8.com/vue-static/landings/line-awesome/font-awesome-line-awesome/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/animate.css@^4.0.0/animate.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.min.css" rel="stylesheet" type="text/css">

</head>
<style>
    .masterInfo
    {
        position: absolute;
        height: 45%;
        width: 30%;
        background-color: white;
        border:4px solid rgb(241, 171, 19);
        border-radius: 5%;
        box-sizing: border-box;
        bottom:5%;


    }
    .masterInfo:nth-of-type(3n)
    {

        margin-right: 0;
    }
    .masterInfo:nth-of-type(4)
    {
        margin-top: 1%;

    }
    .masterInfo:nth-of-type(5)
    {
        margin-top: 1%;

    }
    .masterInfo:nth-of-type(6)
    {
        margin-top: 1%;

    }
    .masterInfo div,img
    {
        position: absolute;
        display: block;
    }
    .masterInfo .masterId
    {

        font-size:80%;
        bottom: 0;
        left:5%;
        font-weight: bold;


    }
    .masterInfo .masterImage
    {
        width: 48%;
        height: 50%;
        top:5%;
        left:2%;
        border-radius: 20%;
        border: 4px solid rgb(241, 171, 19);
        background-color: yellow;
    }
    .masterInfo .masterName
    {
        width:48%;
        height:25%;
        top:5%;
        left:52%;
        text-align: center;
        border-bottom: 4px solid rgb(241, 171, 19);
        box-sizing: border-box;

    }
    .masterInfo .masterName span
    {
        display: block;
        position: relative;

    }
    .masterInfo .masterName .masterNameLabel
    {
        width: 100%;
        height: 30%;
        font-size: 125%;
        color:black;
        font-weight: bold;


    }

    .masterInfo .masterName .masterNameValue
    {
        top:1%;
        width: 100%;
        height: 70%;
        font-size: 100%;
        color:black;
        font-style: italic;

    }

    .masterInfo .masterWorkTime
    {
        top:30%;
        left:52%;
        width: 48%;
        height: 20%;
        text-align: center;
        border-bottom: 4px solid rgb(241, 171, 19);
        box-sizing: border-box;
    }
    .masterInfo .masterWorkTime span
    {

        display: block;
        position: relative;

    }
    .masterInfo .masterWorkTime .WorkTimeLabel
    {
        font-size: 125%;
        font-weight: bold;

    }
    .masterInfo .masterWorkTime .WorkTimeValue
    {
        font-size: 100%


    }
    .masterInfo .masterRating
    {
        bottom:0;
        right:10%;
        text-align: center;


    }
    .masterInfo .masterRating span
    {

        display: block;
        position: relative;
        float:left;


    }
    .masterInfo .masterRating .RatingLabel
    {
        font-size: 100%;
        font-weight: bold;
        font-style: italic;

    }
    .masterInfo .masterRating .RatingValue
    {
        font-size: 100%;

        left:5%;

        font-weight: bold;

    }
    .masterInfo .masterServices
    {

        bottom: 17%;
        width: 100%;
        height: 25%;
        border-bottom: 1px solid rgb(241, 171, 19);
        text-align: center;


    }
    .masterInfo .masterServices span
    {
        display: block;
        position: relative;

    }
    .masterInfo .masterServices .masterServicesLabel
    {
        width: 100%;
        font-size: 125%;
        font-weight: bold;

    }
    .masterInfo .masterServices .masterServiceValues
    {    width: 100%;
        font-size: 100%;
        font-style: italic;

    }

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
    /*.CirclePickLeft:hover .tipForDisplayingTime{*/
    /*    display: block;*/
    /*}*/
    /*.CirclePickRight:hover .tipForDisplayingTime{*/
    /*    display: block;*/
    /*}*/
    .serviceOption
    {
        position: absolute;
        top:25%;
        left:1%;
    }
    #masterNameInputField{
        position: absolute;
        top:15%;
        left: 1%;
    }
    #servicePriceHolderInput{
        position: absolute;
        top:25%;
        left:9%;
    }
    .inputsForms{
        color:rgb(241, 171, 19);
        border-bottom-style: groove;
        border-color: rgb(241, 171, 19);
        background-color: white;
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
    label[for="masterNameInputField"]{
        position: absolute;
        top:11%;
        left: 4%;
    }
    label[for="servicePriceHolderInput"]{
        position: absolute;
        top:21%;
        left:7%;
    }
    label{
        color:rgb(241, 171, 19);
        border-bottom-style: groove;
        border-bottom-color: rgb(241, 171, 19);
        background-color: white;
    }
    #dayChooseLabel{
        position: absolute;
        top:11%;
        left:18%;
    }
    #dayChoosing{
        position: absolute;
        top:15%;
        left:18%;
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
    #placeMessageCenter{
        position: absolute;
        left:50%;
        top:50%;
    }
    #messageToClient{
        position: relative;
        left:-50%;
        top:-50%;
        background-color: rgb(232, 232, 232);
        border:4px solid red;
        color:red;
        display:none;
        text-align: center;
        z-index: 100;
    }
    #makeOrderButton{
        position: absolute;
        left:5%;
        top:44%;
        color:rgb(241, 171, 19);

    }

</style>
<body>
<label id = "dayChooseLabel" for="dayChoosing"><fmt:localeValue key="DayChoosing"/></label>
<script src="https://cdn.jsdelivr.net/npm/vue@^2.0.0/dist/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.umd.modern.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<div id="q-app">
    <div>
        <%@include file="../headerandfoter/header.jsp"%>
    </div>
</div>

<label for="masterNameInputField"  ><fmt:localeValue key="masterName"/></label>
<input id="masterNameInputField" class="inputsForms" disabled="disabled"  placeholder="<fmt:localeValue key="masterName"/>" size = 25 value="${requestScope.master.masterName}"><br>
<label for="servicePriceHolderInput"  ><fmt:localeValue key="services"/></label>
<input class="inputsForms" id="servicePriceHolderInput" disabled="disabled" placeholder="<fmt:localeValue key="servicePrice"/>" size = 25>


    <div class = "masterInfo"  >
        <div class = "masterId"> master id :${requestScope.master.masterId}</div>
        <img class = "masterImage" src="${pageContext.request.contextPath}/masterPicture.png">
        <div class = "masterName"> <span class ="masterNameLabel"><fmt:localeValue key="masterName"/></span> <span class="masterNameValue" >${requestScope.master.masterName}</span> </div>
        <div class = "masterWorkTime"> <span class ="WorkTimeLabel"><fmt:localeValue key="workTime"/></span> <span class = "WorkTimeValue" > ${requestScope.master.startTime}  &nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;   ${requestScope.master.endTime}</span> </div>
        <div class="masterServices" > <span class="masterServicesLabel" ><fmt:localeValue key="services"/></span><span class ="masterServiceValues">
            <c:forEach var="testService" varStatus="loop" items="${requestScope.service}" >
                ${testService.serviceType}
                <c:if test="${not loop.last}">
                    <c:out value=","/>
                </c:if>
            </c:forEach>
        </span>
        </div>
        <div class = "masterRating"> <span class ="RatingLabel"><fmt:localeValue key="RATING"/>:</span> <span class ="RatingValue" > ${master.rating}</span> </div>
    </div>



<select id="serviceOptionsType" class="serviceOption" >
<c:forEach var="serviceOption"  items="${requestScope.service}" >
<option value="<c:out value="${serviceOption.servicePrice}"/> ${serviceOption.serviceId}"><c:out value="${serviceOption.serviceType}"/></option>
</c:forEach>
</select>

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
<button form="makeOrderForm" id="makeOrderButton"><fmt:localeValue key="singUpForAService"/></button>

<div id="placeMessageCenter" >
    <div id="messageToClient">
    </div>
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
                window.location.href = window.location.href+'?lang=en&userId=${param.userId}&masterId=${param.masterId}'
            },
            ChangeToUa(){
                window.location.href = window.location.href+'?lang=ua&userId=${param.userId}&masterId=${param.masterId}'
            },
            ChangeToRu(){
                window.location.href = window.location.href+'?lang=ru&userId=${param.userId}&masterId=${param.masterId}'
            },
            goHome(){
                window.location.replace('${pageContext.request.contextPath}'+'/pages/index?lang=${sessionScope.lang}');
            }
        }
    })





    var serviceOption;
    var servicePriceHolder;
    var dayChoose;
    var pixelForMinutes = 2;
    var arrayOfStartTimes = [];
    var arrayOfEndTimes = [];
    var makeOrderButton;
    var messageToShowContainer;
    var messageToShow;
    window.addEventListener('load',()=>{
        serviceOption = document.getElementById("serviceOptionsType");
        dayChoose = document.getElementById("dayChoosing");
        servicePriceHolder = document.getElementById("servicePriceHolderInput");
        serviceOption.addEventListener("change",(event)=>{
            let stringValue = event.target.options[event.target.selectedIndex].value;
            let price = stringValue.substring(0,stringValue.indexOf(' '));
            servicePriceHolder.value = price;
        })
        messageToShowContainer = document.getElementById('messageToClient');
        messageToShow = '${param.errorMessageTo}';
        if(messageToShow==='timeNot')
        {
            messageToShowContainer.innerText = '<fmt:localeValue key="whenTimeIsNotChoose"/>';
            messageToShowContainer.style.display = 'block';
            setTimeout(()=>{
                messageToShowContainer.style.display = 'none';
            },2000);
        }
        else if(messageToShow==='insertedAnOrder')
        {
            messageToShowContainer.style.color  = "green";
            messageToShowContainer.style.borderColor = "green";
            messageToShowContainer.innerText = '<fmt:localeValue key="whenInsertedAnOrder"/>';
            messageToShowContainer.style.display = 'block';
            setTimeout(()=>{
                messageToShowContainer.style.display = 'none';
            },2000);
        }
        else
        {
            messageToShowContainer.innerText = messageToShow;
            messageToShowContainer.style.display = 'block';
            setTimeout(()=>{
                messageToShowContainer.style.display = 'none';
            },2000);
        }
        makeOrderButton = document.getElementById('makeOrderButton');
 makeOrderButton.addEventListener('click',(event)=>{
     let startTime = document.getElementById('startTimeTo').value;
     let endTime = document.getElementById('endTimeTo').value;
     let dayDateValue = dayChoose.options[dayChoose.selectedIndex].text;
     let isApplied = false;
     let isDone = false;
     let stringValue = serviceOption.options[serviceOption.selectedIndex].value;
     let serviceId = stringValue.substring(stringValue.indexOf(' ')+1);
     event.target.formAction = '${pageContext.request.contextPath}/pages/client/makePostRecord?lang=${sessionScope.lang}&masterId=${param.masterId}&userId=${param.userId}&startTime='+startTime+'&endTime='+endTime+'&dayDate='+dayDateValue+'&isApplied='+isApplied+'&isDone='+isDone+'&serviceId='+serviceId;

 })

        <c:set var = "topOffsetTimeLineOffset" scope="request" value = "53"/>
        <c:forEach var="startTime" items="${requestScope.startTimeSlots}" varStatus="loop" >
           arrayOfStartTimes[${loop.index}] = ${startTime}
           arrayOfEndTimes[${loop.index}] = ${requestScope.endTimeSlots.get(loop.index)}
           createTimeLine(arrayOfStartTimes[${loop.index}],arrayOfEndTimes[${loop.index}],${topOffsetTimeLineOffset},'${requestScope.dateOfTimeLine.get(loop.index).toString()}');
           <c:set var="topOffsetTimeLineOffset" scope="request" value="${topOffsetTimeLineOffset+10}"/>
        </c:forEach>
        <c:remove var="topOffsetTimeLineOffset" scope="request"/>

    })

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

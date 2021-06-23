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

    .pageNumberHolder{
        width: 40px;
    }
    .sliderArrows{
        position: relative;
        left:10%;
    }
    .freeTimeSlots{

        color:orange;
        float: left;
        text-align: center;
    }
    .freeTimeSlots span{
        display: block;
        position: relative;
        left:10%;
        font-size: larger;

    }
    .notFreeTimeSlots span{
        display: block;
        position: relative;
        left:10%;
        text-align: center;
        font-size: larger;

    }
    .notFreeTimeSlots{
        color: orange;
        position: relative;
        margin-left: 10%;
        float: left;
    }
    .freeTimeSlots table{
        border: 4px solid orange;
        border-radius: 20px;
    }
    th{
        font-size: larger;
        text-align: center;
        border-bottom: 1px solid orange;
        color:blue;
    }
    td{
        font-size: medium;
        text-align: center;
        color:blue;
    }
    .notFreeTimeSlots table{
        border: 4px solid orange;
        border-radius: 20px;
    }
</style>



<body>
<script src="https://cdn.jsdelivr.net/npm/vue@^2.0.0/dist/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.umd.modern.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<div id="q-app">
    <div>
        <%@include file="../headerandfoter/header.jsp"%>
    </div>
</div>
<div class="freeTimeSlots" >
    <span> Free Time Slots</span>
    <table >
        <tr>
            <th> timeSlotId </th>
            <th> masterId </th>
            <th> <fmt:localeValue key="startTimeSlot"/> </th>
            <th> <fmt:localeValue key="endTimeSlot"/> </th>
            <th> <fmt:localeValue key="isFree"/> </th>
        </tr>
    <c:forEach var="timeSlot" items="${requestScope.timeSlotsFree}">
        <tr>
            <td>${timeSlot.timeSlotId}</td>
            <td>${timeSlot.masterId}</td>
            <td>${timeSlot.startTime.toLocalDate()} ${timeSlot.startTime.toLocalTime()}</td>
            <td>${timeSlot.endTime.toLocalDate()} ${timeSlot.endTime.toLocalTime()}</td>
            <td>1</td>
        </tr>
    </c:forEach>
    </table>
    <div class="sliderArrows" >
        <button  id="leftArrowPageFree" class="buttonArrows">&lt;</button>
        <input id="pageNumberHolderFree" disabled="disabled" class="pageNumberHolder" type="text">
        <button id="RightArrowPageFree" class="buttonArrows">&gt;</button>
    </div>
</div>

<div class="notFreeTimeSlots" >
    <span> not Free Time Slots</span>
    <table >
        <tr>
    <th> timeSlotId </th>
    <th> masterId </th>
    <th> <fmt:localeValue key="startTimeSlot"/> </th>
    <th> <fmt:localeValue key="endTimeSlot"/> </th>
    <th> <fmt:localeValue key="isFree"/> </th>
    <th> <fmt:localeValue key="Done"/> </th>
  </tr>
        <c:forEach var="timeSlot" items="${requestScope.timeSlotsNotFree}">
            <tr>
                <td>${timeSlot.timeSlotId}</td>
                <td>${timeSlot.masterId}</td>
                <td>${timeSlot.startTime.toLocalDate()} ${timeSlot.startTime.toLocalTime()}</td>
                <td>${timeSlot.endTime.toLocalDate()} ${timeSlot.endTime.toLocalTime()}</td>
                <td>0</td>
                <td><a href="${pageContext.request.contextPath}/pages/master/markSlotAsDone?timeSlotId=${timeSlot.timeSlotId}" ><fmt:localeValue key="markAsDone"/></a></td>
            </tr>
        </c:forEach>
    </table>
    <div class="sliderArrows" >
        <button  id="leftArrowPage" class="buttonArrows">&lt;</button>
        <input id="pageNumberHolderNotFree"  disabled="disabled" class="pageNumberHolder" type="text">
        <button id="RightArrowPage" class="buttonArrows">&gt;</button>
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

    var count1;
    var pageNumber1;
    var pageNumberHolder1;
    var arrowMoveLeft1;
    var arrowMoveRight1;
    var count2;
    var pageNumber2;
    var pageNumberHolder2;
    var arrowMoveLeft2;
    var arrowMoveRight2;
    window.addEventListener('load',()=>{
         count1 = ${requestScope.count1}
        pageNumber1 = ${requestScope.page1}
        count2 = ${requestScope.count2}
        pageNumber2 = ${requestScope.page2}
            arrowMoveLeft1 = document.getElementById('leftArrowPageFree');
        arrowMoveRight1 = document.getElementById('RightArrowPageFree');
        arrowMoveLeft2 = document.getElementById('leftArrowPage');
        arrowMoveRight2 = document.getElementById('RightArrowPage');
        pageNumberHolder1 = document.getElementById('pageNumberHolderFree');
        pageNumberHolder2 = document.getElementById('pageNumberHolderNotFree');
        pageNumberHolder1.value = pageNumber1;
        if(pageNumber1>=count1)arrowMoveRight1.disabled=true;
        if(pageNumber1<=1)arrowMoveLeft1.disabled=true;
        pageNumberHolder2.value = pageNumber2;
        if(pageNumber2>=count2)arrowMoveRight2.disabled=true;
        if(pageNumber2<=1)arrowMoveLeft2.disabled=true;

        arrowMoveLeft1.addEventListener('click',()=>{
            pageNumber1--;
            window.location.href = '${pageContext.request.contextPath}/pages/master/scheduleSlots?pageNumber1='+pageNumber1+'&lang=${sessionScope.lang}&pageNumber2='+pageNumber2;
        });
        arrowMoveRight1.addEventListener('click',()=>{
            pageNumber1++;
            window.location.href = '${pageContext.request.contextPath}/pages/master/scheduleSlots?pageNumber1='+pageNumber1+'&lang=${sessionScope.lang}&pageNumber2='+pageNumber2;
        });
        arrowMoveRight2.addEventListener('click',()=>{
            pageNumber2++;
            window.location.href = '${pageContext.request.contextPath}/pages/master/scheduleSlots?pageNumber2='+pageNumber2+'&lang=${sessionScope.lang}&pageNumber1='+pageNumber1;
        });
        arrowMoveLeft2.addEventListener('click',()=>{
            pageNumber2--;
            window.location.href = '${pageContext.request.contextPath}/pages/master/scheduleSlots?pageNumber2='+pageNumber2+'&lang=${sessionScope.lang}&pageNumber1='+pageNumber1;
        });
    });


</script>
</html>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="/WEB-INF/ResourceBundleUTF.tld"%>
<%@taglib prefix="log" uri="http://logging.apache.org/log4j/tld/log"%>
<log:setLogger logger="indexJsp" var="indexJsp"/>
<log:log level="info" logger="${indexJsp}" message="Index jsp has been visited"/>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang :pageContext.request.locale.language}" scope="session" />
<fmt:setLocale name="${lang}" />
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html lang="${lang}">
<head>
    <title>index</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet" type="text/css">
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet" type="text/css">
    <link href="https://maxst.icons8.com/vue-static/landings/line-awesome/font-awesome-line-awesome/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/animate.css@^4.0.0/animate.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.min.css" rel="stylesheet" type="text/css">
</head>
<style>
    .mastersInfo
    {
        position: absolute;
        width:85%;
        height:80%;
        left:13%;
        top:18%;



    }
    .masterInfo
    {
        position: relative;
        height: 45%;
        width: 30%;
        background-color: white;
        border:4px solid rgb(241, 171, 19);
        border-radius: 5%;
        box-sizing: border-box;
        margin-right: 5%;
        float: left;
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

    .masterInfo .linkToMasterService
    {

        position: absolute;
        display: block;
        width: 40%;
        height: 30%;
        background-color: rgb(241, 171, 19);
        color:white;
        text-decoration-line: none;
        border-radius: 20%;
        font-weight: bold;
        text-align: center;
        left:25%;
        bottom: -50%;


    }
    #mastersSortSelect
    {
        position: absolute;

        left:1%;
        top:10%;

    }
    #filterByNameMasterText
    {
        position: absolute;
        top:10%;
        left:25%;



    }
    #filterByNameMasterClick
    {
        position: absolute;
        top:10%;
        left:43%;


    }
    #filterByServiceMasterText
    {
        position: absolute;
        top:10%;
        left:57%;



    }
    #filterByServiceMasterClick
    {
        position: absolute;
        top:10%;
        left:78%;



    }
    .pagesChanger
    {
        position: absolute;
        bottom: 7%;
        width: 100%;


    }

    .arrowMasterMovePage{

        position: absolute;
        right: 20%;
    }
    #moveRightByOnePage{
        right: 14.8%;
    }
    #NumberOfPageMaster
    {
        position: absolute;
        right: 17%;
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


<select id ="mastersSortSelect"  >
    <option value="ratingASC"><fmt:localeValue key="SortbyratingASC"/></option>
    <option value="masterNameASC"><fmt:localeValue key="SortbynameASC"/></option>
    <option value="ratingDESC"><fmt:localeValue key="SortbyratingDESC"/></option>
    <option value="masterNameDESC"><fmt:localeValue key="SortbynameDESC"/></option>
</select>

<input id="filterByNameMasterText" type="text" size="30" placeholder="<fmt:localeValue key="inputnametofilter"/>" >

<button type="button" id="filterByNameMasterClick" ><fmt:localeValue key="filterbyname"/></button>

<input id="filterByServiceMasterText" type="text" size="35" placeholder="<fmt:localeValue key="inputservicetofilter"/>" >

<button type="button" id="filterByServiceMasterClick" ><fmt:localeValue key="filterbyservice"/></button>

<div class="mastersInfo" >
<c:forEach var="master" items="${requestScope.mastersList}">
    <div class = "masterInfo"  >
        <div class = "masterId"> master id :${master.masterId}</div>
        <img class = "masterImage" src="${pageContext.request.contextPath}/masterPicture.png">
        <div class = "masterName"> <span class ="masterNameLabel"><fmt:localeValue key="masterName"/></span> <span class="masterNameValue" >${master.masterName}</span> </div>
        <div class = "masterWorkTime"> <span class ="WorkTimeLabel"><fmt:localeValue key="workTime"/></span> <span class = "WorkTimeValue" > ${master.startTime}  &nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;   ${master.endTime}</span> </div>
        <div class="masterServices" > <span class="masterServicesLabel" ><fmt:localeValue key="services"/></span><span class ="masterServiceValues">
            <c:forEach var="testService" varStatus="loop" items="${requestScope.servicesMap.get(master.masterId)}" >
                ${testService.serviceType}
                <c:if test="${not loop.last}">
                    <c:out value=","/>
                </c:if>
            </c:forEach>
        </span>
            <a class="linkToMasterService" href="${pageContext.request.contextPath}/pages/client/makeRecord?masterId=${master.masterId}&lang=${sessionScope.lang}&userId=${sessionScope.userId}&pageN=${requestScope.pageNumber}"><fmt:localeValue key="singUpForAService"/></a>
        </div>
        <div class = "masterRating"> <span class ="RatingLabel"><fmt:localeValue key="RATING"/>:</span> <span class ="RatingValue" > ${master.rating}</span> </div>
    </div>
</c:forEach>

    <div class="pagesChanger" >
        <input type="text" id="NumberOfPageMaster" size="1">
        <button type="button" class="arrowMasterMovePage" id="moveLeftByOnePage" >&lt;</button>
        <button type="button" class ="arrowMasterMovePage" id="moveRightByOnePage" >&gt;</button>
    </div>

</div>

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
                let tempHref = window.location.href+'?lang=en&sortBY='+sortByColumn+'&orderingType='+ordertingType+'&pageNumber='+numberOfPageAttribute;
                   if(nameToFilterBy!=null&&nameToFilterBy!=='')tempHref+='&filterBYName='+nameToFilterBy;
                   if(serviceNameToFilterBy!=null&&serviceNameToFilterBy!=='')tempHref+='&filterBYService='+serviceNameToFilterBy;
                  window.location.href = tempHref;
            },
            ChangeToUa(){
                let tempHref = window.location.href+'?sortBY='+sortByColumn+'&orderingType='+ordertingType+'&pageNumber='+numberOfPageAttribute;
                if(nameToFilterBy!=null&&nameToFilterBy!=='')tempHref+='&filterBYName='+nameToFilterBy;
                if(serviceNameToFilterBy!=null&&serviceNameToFilterBy!=='')tempHref+='&filterBYService='+serviceNameToFilterBy;
                tempHref+='&lang=uk';
                window.location.href = tempHref;
            },
            ChangeToRu(){
                 let tempHref = window.location.href+'?sortBY='+sortByColumn+'&orderingType='+ordertingType+'&pageNumber='+numberOfPageAttribute;
                 if(nameToFilterBy!=null&&nameToFilterBy!=='')tempHref+='&filterBYName='+nameToFilterBy;
                 if(serviceNameToFilterBy!=null&&serviceNameToFilterBy!=='')tempHref+='&filterBYService='+serviceNameToFilterBy;
                 tempHref+='&lang=ru';
                window.location.href = tempHref;
            },
            goHome(){
                window.location.replace('${pageContext.request.contextPath}'+'/pages/index?lang=${sessionScope.lang}');
            },
            RegistrationPage(){
                window.location.href ='${pageContext.request.contextPath}'+'/pages/guest/registration?lang=${sessionScope.lang}';

            },
            checkRecords(){
                window.location.href='${pageContext.request.contextPath}'+'/pages/admin/records?lang=${sessionScope.lang}';
            },
            checkSchedule(){
                window.location.href='${pageContext.request.contextPath}'+'/pages/master/scheduleSlots?lang=${sessionScope.lang}';
            }

        }
    })




    var elementSortMastersBy;
    var elementFIlterByName;
    var elementFIlterByService;
    var elementToMoveLeft ;
    var elementToMoveRight;
    var pageNumberOfMasterTable;
    var count;
    var numberOfPageAttribute;
    var nameToFilterBy;
    var serviceNameToFilterBy;
    var sortByColumn;
    var ordertingType;
    var messageTo ;
    var messageHolder;
    window.addEventListener('load',()=>{
           messageTo = '${param.errorMessageTo}';
            if(messageTo !== '')
            {   let messageToShow = '';
                messageHolder = document.getElementById('messageToClient');
                if(messageTo==="userType"){
                    messageToShow = '<fmt:localeValue key="messageNotCorrectUserType"/> ${sessionScope.userType}';
                }
                else if(messageTo==="NotEntered"){
                    messageToShow = '<fmt:localeValue key="messageWhenNotEntered"/>';
                }
                else
                {
                    messageToShow = messageTo;
                }
                messageHolder.innerText = messageToShow;
                messageHolder.style.display = "block";
                setTimeout(()=>{
                    messageHolder.style.display  = "none";
                },2000)
            }

        elementSortMastersBy = document.getElementById('mastersSortSelect');
        elementFIlterByName = document.getElementById('filterByNameMasterClick');
        elementFIlterByService = document.getElementById('filterByServiceMasterClick');
        elementToMoveLeft = document.getElementById("moveLeftByOnePage");
        elementToMoveRight = document.getElementById("moveRightByOnePage");
        pageNumberOfMasterTable = document.getElementById('NumberOfPageMaster');
        count = ${requestScope.pagesCount}
        numberOfPageAttribute = ${requestScope.pageNumber}
        pageNumberOfMasterTable.disabled = true;
        pageNumberOfMasterTable.value = numberOfPageAttribute;
        nameToFilterBy='${requestScope.filterName}'
        serviceNameToFilterBy = '${requestScope.filterService}'
        sortByColumn = '${requestScope.sort}'
        ordertingType = '${requestScope.orderType}'
        for(let i=0;i<elementSortMastersBy.length;i++){

            if(elementSortMastersBy.options[i].value===sortByColumn+ordertingType)
            {elementSortMastersBy.selectedIndex=i;}
        }
         if(count===0)
         {
             elementToMoveLeft.disabled=true;
             elementToMoveRight.disabled = true;
         }

         if(numberOfPageAttribute>=count)
         {
             elementToMoveRight.disabled = true;

         }
        if(numberOfPageAttribute>count||numberOfPageAttribute===1)
        {
            elementToMoveLeft.disabled = true;
        }

        elementToMoveLeft.addEventListener('click',()=>{
            //sortBY[rating,masterName],orderingType[ASC|DESC],filterBYName filterBYService pageNumber pagesCount
            let hrefLocationTemp = '${pageContext.request.contextPath}'+'/pages/index?lang=${sessionScope.lang}'+
                '&sortBY='+sortByColumn+'&orderingType='+ordertingType;
            if(nameToFilterBy!=null&&nameToFilterBy!=='') {
                hrefLocationTemp += '&filterBYName=' + nameToFilterBy;

            }
            if(serviceNameToFilterBy!=null&&serviceNameToFilterBy!=='')
            {
                hrefLocationTemp+='&filterBYService='+serviceNameToFilterBy;
            }
            numberOfPageAttribute-=1;
            hrefLocationTemp+='&pageNumber='+numberOfPageAttribute;
            window.location.href = hrefLocationTemp;

        })

        elementToMoveRight.addEventListener('click',()=>{
            //sortBY[rating,masterName],orderingType[ASC|DESC],filterBYName filterBYService pageNumber pagesCount
          let hrefLocationTemp = '${pageContext.request.contextPath}'+'/pages/index?lang=${sessionScope.lang}'+
                '&sortBY='+sortByColumn+'&orderingType='+ordertingType;
            if(nameToFilterBy!=null&&nameToFilterBy!=='') {
                hrefLocationTemp += '&filterBYName=' + nameToFilterBy;

            }
            if(serviceNameToFilterBy!=null&&serviceNameToFilterBy!=='')
            {
                hrefLocationTemp+='&filterBYService='+serviceNameToFilterBy;
            }
            numberOfPageAttribute+=1;
            hrefLocationTemp+='&pageNumber='+numberOfPageAttribute;
            window.location.href = hrefLocationTemp;

        })
        elementFIlterByName.addEventListener('click',(event)=>{

            let name = document.getElementById('filterByNameMasterText').value.trim();
            if(name!='' && name!= null)
            {

            let hrefLocationTemp = '${pageContext.request.contextPath}'+'/pages/index?lang=${sessionScope.lang}'+
                    '&sortBY='+sortByColumn+'&orderingType='+ordertingType+'&filterBYName=' + name;
                  if(serviceNameToFilterBy!=null&&serviceNameToFilterBy!=='')hrefLocationTemp+='&filterBYService='+serviceNameToFilterBy;
                window.location.href = hrefLocationTemp;
            }
        });
        elementFIlterByService.addEventListener('click',(event)=>{

            let serviceName = document.getElementById('filterByServiceMasterText').value.trim();
            if(serviceName!='' && serviceName!= null)
            {
                let hrefLocationTemp = '${pageContext.request.contextPath}'+'/pages/index?lang=${sessionScope.lang}'+
                    '&sortBY='+sortByColumn+'&orderingType='+ordertingType+'&filterBYService=' + serviceName;
                if(nameToFilterBy!=null&&nameToFilterBy!=='')hrefLocationTemp+='&filterBYName=' + nameToFilterBy;
                window.location.href = hrefLocationTemp;

            }
        });
        elementSortMastersBy.addEventListener('change',(event)=>{

            let selected = event.target;
            let hrefTemp = '${pageContext.request.contextPath}'+'/pages/index?lang=${sessionScope.lang}';
            if(nameToFilterBy!=null&&nameToFilterBy!=='')hrefTemp+='&filterBYName='+nameToFilterBy;

            if(serviceNameToFilterBy!=null&&serviceNameToFilterBy!=='')hrefTemp+='&filterBYService='+serviceNameToFilterBy;

            if(selected.options[selected.selectedIndex].value === "ratingASC")
            {

                          hrefTemp+='&sortBY=rating&orderingType=ASC';
            }
            else if(selected.options[selected.selectedIndex].value === "masterNameASC")
            {
                         hrefTemp+='&sortBY=masterName&orderingType=ASC';

            }
            else if(selected.options[selected.selectedIndex].value === "ratingDESC")
            {

                       hrefTemp+='&sortBY=rating&orderingType=DESC';

            }
            else if(selected.options[selected.selectedIndex].value === "masterNameDESC")
            {

                  hrefTemp+='&sortBY=masterName&orderingType=DESC';

            }

         window.location.href = hrefTemp;
        });


    });








</script>
</html>
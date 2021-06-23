<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/ResourceBundleUTF.tld"%>
<%@taglib prefix="log" uri="http://logging.apache.org/log4j/tld/log"%>
<log:setLogger logger="registrJsp" var="registrJsp"/>
<log:log level="info" logger="${registrJsp}" message="Registration jsp has been visited"/>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang :pageContext.request.locale}" scope="session" />
<fmt:setLocale name="${lang}" />
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html lang="${lang}">
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet" type="text/css">
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet" type="text/css">
    <link href="https://maxst.icons8.com/vue-static/landings/line-awesome/font-awesome-line-awesome/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/animate.css@^4.0.0/animate.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.min.css" rel="stylesheet" type="text/css">
    <script>
        window.quasarConfig = {
            brand: {
                primary: '#003cd4',
                secondary: '#26a679',
                accent: '#9C27B0',
                dark: '#1d1d1d',
                positive: '#e3ffc9',
                negative: '#C10015',
                info: '#32eda5',
                warning: '#ffbb00'
            }
        }
    </script>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/vue@^2.0.0/dist/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.umd.modern.min.js"></script>
<div id="q-app">

    <div>
        <%@include file="../headerandfoter/header.jsp"%>
    </div>
    <div class="q-pa-md row justify-center">
        <div class="col-6">
            <q-card style="left: 15px" class="q-ma-md col-5 bg-yellow-14">
                <q-card-section>
                    <div class="text-center text-h5"><fmt:localeValue key="registrationLabel"/></div>
                </q-card-section>
                <q-card-section class="text-center q-pt-none">
                    <fmt:localeValue key="registrationCreateAccountTitle"/>
                </q-card-section>
            </q-card>

                    <q-form action="masterMakeRegistration" method="post">
                        <q-input
                                filled
                                debounce="300"
                                type="text"
                                :hint="LogHint"
                                v-model="login"
                                label="<fmt:localeValue key="dialogLogin"/>"
                                name="login"
                                class="q-ma-md"
                                :rules="[ val => val.match(/(^([a-z-а-яА-ЯЇЄЁЭїєёэ]{5,19})$)/ig)!=null || '<fmt:localeValue key="loginValidation" />']"
                        >
                            <template v-slot:before>
                                <q-icon name="login"></q-icon>
                            </template>
                        </q-input>
                        <q-input
                                filled
                                debounce="300"
                                type="text"
                                :hint="masterHint"
                                v-model="masterName"
                                label="<fmt:localeValue key="dialogMasterName"/>"
                                name="mName"
                                class="q-ma-md"
                                :rules="[ val => val.match(/(^([a-z-а-яА-ЯЇЄЁЭїєёэ]{5,19})$)/ig)!=null || '<fmt:localeValue key="masterNameValidation" />']"
                        >
                            <template v-slot:before>
                                <q-icon name="login"></q-icon>
                            </template>
                        </q-input>
                        <q-input
                                filled
                                debounce="300"
                                type="email"
                                :hint="EmailHint"
                                v-model="model"
                                label="<fmt:localeValue key="registrationEmail"/>"
                                name="Email"
                                class="q-ma-md"
                                :rules="[ val => val.match(/(^([a-z\d]{1,64}@[a-z\d-]{1,200}[\.][a-z]([a-z\d]{1,53}))$)/i)!=null || '<fmt:localeValue key="emailValidation"/>']"
                        >

                            <template v-slot:before>
                                <q-icon name="mail"></q-icon>
                            </template>
                        </q-input>
                        <q-input v-model="password"
                                 class="q-ma-md"
                                 debounce="300"
                                 name="psw"
                                 label="<fmt:localeValue key="dialogPassword"/>"
                                 :rules="[ val => val.match(/(^([\wа-яА-ЯЇЄЁЭїєёэ]{5,20})$)/ig)!=null || '<fmt:localeValue key="passwordValidation"/>']"
                                 filled :type="isPwd ? 'password' : 'text'"
                                 :hint="PasswordHint">
                            <template v-slot:before>
                                <q-icon
                                        :name="isPwd ? 'visibility_off' : 'visibility'"
                                        class="cursor-pointer"
                                        @click="isPwd = !isPwd"
                                ></q-icon>
                            </template>
                        </q-input>
                        <q-input debounce="400" filled name="startTime" label="<fmt:localeValue key="startTime"/>" v-model="startTime" mask="##:##">
                            <template v-slot:append>
                                <q-icon name="access_time" class="cursor-pointer">
                                    <q-popup-proxy transition-show="scale" transition-hide="scale">
                                        <q-time  v-model="startTime" mask="HH:mm" format24h>
                                            <div class="row items-center justify-end">
                                                <q-btn v-close-popup label="Close" color="primary" flat></q-btn>
                                            </div>
                                        </q-time>
                                    </q-popup-proxy>
                                </q-icon>
                            </template>
                        </q-input>
                        <q-input debounce="400" filled name="endTime" label="<fmt:localeValue key="endTime"/>" v-model="endTime" mask="##:##">
                            <template v-slot:append>
                                <q-icon name="access_time" class="cursor-pointer">
                                    <q-popup-proxy transition-show="scale" transition-hide="scale">
                                        <q-time  v-model="endTime" mask="HH:mm" format24h>
                                            <div class="row items-center justify-end">
                                                <q-btn v-close-popup label="Close" color="primary" flat></q-btn>
                                            </div>
                                        </q-time>
                                    </q-popup-proxy>
                                </q-icon>
                            </template>
                        </q-input>

                        <c:forEach items="${requestScope.services}" var="service">
                            <q-checkbox
                                    name="serviceType"
                                    val="${service}"
                                    v-model ="serviceTypes"
                                    label="${service}"
                            ></q-checkbox>
                        </c:forEach>
                        <q-btn style="width:100%;" class="text-black" label="<fmt:localeValue key="registrationCreateAccount"/>" type="submit" color="yellow-14"></q-btn>

                    </q-form>

                </div>

    </div>

</div>
</body>
<script>
    new Vue({
        el: '#q-app',
        data () {
            return {
                serviceTypes:${requestScope.serviceTypes},
                startTime:'',
                endTime:'',
                model: '',
                password: '',
                login:'',
                masterName:'',
                isPwd: true,
                LogHint:'<fmt:localeValue key="LogHintD"/>',
                EmailHint:'<fmt:localeValue key="EmailHint"/>',
                PasswordHint:'<fmt:localeValue key="PasswordHintD"/>',
                masterHint:'<fmt:localeValue key="MasterNameHint"/>',
                passwordD: '',
                loginD:'',
                isPwdD: true,
                LogHintD:'<fmt:localeValue key="LogHintD"/>',
                PasswordHintD:'<fmt:localeValue key="PasswordHintD"/>',
                showDialogD: false,
                Eng:'',
                Ua:'',
                Ru:''
            }
        },
        methods:{
            ChangeToEng(){
                window.location.href = window.location.href+'?lang=en'
            },
            ChangeToUa(){
                window.location.href = window.location.href+'?lang=ua'
            },
            ChangeToRu(){
                window.location.href = window.location.href+'?lang=ru'
            },
            masterRegistration(){
                window.location.href ='${pageContext.request.contextPath}'+'/pages/guest/masterRegistration?lang=${sessionScope.lang}';
            },
            goHome(){
                window.location.replace('${pageContext.request.contextPath}'+'/pages/index?lang=${sessionScope.lang}');
            },
            RegistrationPage(){
                window.location.href ='${pageContext.request.contextPath}'+'/pages/guest/registration?lang=${sessionScope.lang}';

            },
            makeRecord(){
                window.location.href='${pageContext.request.contextPath}'+'/pages/client/makeRecord?lang=${sessionScope.lang}';
            },
            checkRecords(){
                window.location.href='${pageContext.request.contextPath}'+'/pages/admin/records?lang=${sessionScope.lang}';
            }
        },
        mounted: function(){
       console.log('${requestScope.serviceTypes}')
            window.history.pushState({}, document.title,window.location.href.split(/[?#]/)[0]);
            if('${param.registrationMessage}'!==''){

                this.$q.notify({
                    type: 'negative',
                    message: '${param.registrationMessage}',
                    position:'center',
                    icon: 'report_problem'
                })
            }
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

        }
    })
</script>
</html>

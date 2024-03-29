<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
    <c:if test="${'administrator'.equals(sessionScope.userType)}">
        <q-btn size="md" @click="checkRecords()" stretch flat label="<fmt:localeValue key="ClientsRecords"/>"></q-btn>
    </c:if>
    <c:if test="${'master'.equals(sessionScope.userType)}">
        <q-btn size="md" @click="checkSchedule()" stretch flat label="<fmt:localeValue key="MasterSchedule"/>"></q-btn>
    </c:if>

</q-toolbar>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet" type="text/css">
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet" type="text/css">
    <link href="https://maxst.icons8.com/vue-static/landings/line-awesome/font-awesome-line-awesome/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/animate.css@^4.0.0/animate.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/vue@^2.0.0/dist/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.umd.modern.min.js"></script>
<%@include file="../headerandfoter/header.jsp"%>
<div id="q-app">
    <div>
        <%@include file="../headerandfoter/header.jsp"%>
    </div>
    <div class="q-pa-md row justify-center">
        <div class="col-10">
            <q-card  class="q-ma-md col">
                <q-card-section class="text-center text-h5 q-pt-none">
                   Masters
                </q-card-section>
            </q-card>
            <q-table
                     title="Masters"
                     no-data-label="no data"
                     no-results-label="no result"
                     :data="data"
                     :columns="columns"
                     :pagination.sync="pagination"
                     :loading="loading"
                     :filter="filter"
                     @request="onRequest"
                     binary-state-sort
            >
                <template v-slot:top-row>
                    <q-tr>
                        <q-td>
                        </q-td>
                        <q-td>
                            <q-input type="text" debounce="300" outlined v-model="filter.masterName" label="Filrte" dense></q-input>
                        </q-td>
                        <q-td>
                            <q-select
                                    filled
                                    v-model="filter.service"
                                    multiple
                                    :options="options"
                                    label="Multiple"
                                    style="width: 250px"
                            ></q-select>
                        </q-td>



                    </q-tr>
                </template>

            </q-table>

        </div>
    </div>
</div>

<a href="TestServetTest">Hello Servlet</a>
</body>
<script>

            const dataTable = parseInt('${Count}',10);
            new Vue({
                el: '#q-app',
                data () {
                    return {
                        Eng:'',
                        Ua:'',
                        Ru:'',
                        multiple:null,
                        filter: {
                            masterName: '',
                            service:null,

                        },
                        loading: false,
                        pagination: {
                            sortBy: 'masterName',
                            descending: false,
                            page: 1,
                            rowsPerPage: 3,
                            rowsNumber: dataTable
                        },
                        columns: [
                            {
                                name: 'masterName',
                                required: true,
                                label: 'MasterName',
                                align: 'left',
                                field: 'masterName',
                                sortable: true
                            },
                            { name: 'rating', align: 'center', label: 'Rating', field: 'rating', sortable: true },
                            { name: 'service', label: 'Services', field: 'service', sortable: true },

                        ],

                        data: []

                    }
                },
                mounted: function(){
                    window.history.pushState({}, document.title,window.location.href.split(/[?#]/)[0]);
                    this.onRequest({
                        pagination: this.pagination,
                        filter: this.filter
                    })
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
                    async onRequest(props) {

                        const {page, rowsPerPage, sortBy, descending} = props.pagination
                        const filter = Object.fromEntries(Object.entries(props.filter).filter(([_, v]) => !!v));

                        this.loading = true


                        // update rowsCount with appropriate value

                        // get all rows if "All" (0) is selected


                        // calculate starting row of data
                        const startRow = (page - 1) * rowsPerPage

                        // fetch data from "server"
                        const returnedData = await this.fetchFromServer(startRow, filter, sortBy, descending, rowsPerPage)
                        if(Object.keys(filter).length!==0){
                            this.pagination.rowsNumber = returnedData.length
                        }

                        // clear out existing data and add new
                        this.data.splice(0, this.data.length, ...returnedData)

                        // don't forget to update local pagination object
                        this.pagination.page = page
                        this.pagination.rowsPerPage = rowsPerPage
                        this.pagination.sortBy = sortBy
                        this.pagination.descending = descending

                        // ...and turn of loading indicator
                        this.loading = false

                    },


                    // SELECT * FROM ... WHERE...LIMIT...
                    async fetchFromServer (startRow,filter, sortBy, descending,rowsPerPage) {
                        const response = await axios.get("${pageContext.request.contextPath}"+"/pages/admin/ordersJson",{
                            params: {
                                startRow: startRow,
                                filter:filter,
                                sortBy:sortBy,
                                descending:descending,
                                rowsPerPage:rowsPerPage
                            }
                        })
                        return response.data
                    },
                    ChangeToEng(){
                        window.location.href = window.location.href+'?lang=en'
                    },
                    ChangeToUa(){
                        window.location.href = window.location.href+'?lang=ua'
                    },
                    ChangeToRu(){
                        window.location.href = window.location.href+'?lang=ru'
                    },
                    goHome(){
                        window.location.replace('${pageContext.request.contextPath}'+'/pages/index?lang=${sessionScope.lang}');
                    },
                    RegistrationPage(){
                        window.location.href ='${pageContext.request.contextPath}'+'/pages/guest/registration?lang=${sessionScope.lang}';

                    },
                    MakeOrder(){
                        window.location.href='${pageContext.request.contextPath}'+'/pages/user/order?lang=${sessionScope.lang}';
                    },
                    CheckOrders(){
                        window.location.href='${pageContext.request.contextPath}'+'/pages/admin/orders?lang=${sessionScope.lang}';
                    }




                }
            })

</script>
</html>
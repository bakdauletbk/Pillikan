package kz.smartideagroup.pillikan.common.remote

object EndPoints {
    //Авторизация
    const val LOGIN = "login"
    //Клиент
    const val GET_PROFILE = "user/get/profile"
    const val UPDATE_PASS = "user/update-password"
    const val GET_MANAGER = "user/district/{id}"
    const val GET_MANAGER_PHOTO = "user/get-image/"
    const val GET_PROMOTIONS = "offer"
    const val IMAGE_BUCKET = "file/"
    const val GET_PROMOTION_DETAILS = "offer/{id}"
    const val GET_CHECKLIST_CONTENT = "checklist/filial/{id}"
    const val SET_CHECKLIST_ANSWER = "checklist/answer"
    const val GET_REPORTS = "complaint/my"
    const val GET_REPORT_DETAILS = "complaint/{id}"
    const val ADD_NEW_REPORT = "complaint"
    const val GET_ALL_CLIENT_ORDERS = "order"
    const val GET_ORDER_BY_ID = "order/{orderId}"
    const val CREATE_RECLAMATION = "reclamation"
    const val GET_ALL_RECLAMATION = "reclamation"
    const val GET_RECLAMATION_BY_ID = "reclamation/{id}"
    //Менеджер
    const val GET_CURRENT_ROUTE ="route/current"
}
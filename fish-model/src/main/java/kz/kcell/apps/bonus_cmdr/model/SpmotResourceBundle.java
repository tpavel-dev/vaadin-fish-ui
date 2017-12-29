package kz.kcell.apps.bonus_cmdr.model;

import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.resource.ResourceBundle;
import lombok.extern.log4j.Log4j;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 16 11 2014
 */
@Log4j
//@ConfigurationProperties(prefix = "SpmotResourceBundle")
//@Component
public enum SpmotResourceBundle implements ResourceBundle {

    _0("")
    ,notification_label              ("")
    ,msisdn_menu_title               ("")
    ,menu_button                     ("Меню - Sales Point Motivation")
    ,title_label                     ("titile_label")
    ,login_title                      ("Вход"
            ,"Кіру")

    ,login_msisdn_field_caption       ("Введите логин"
            ,"Сатушы нөмірін енгізіңіз (Activ)")

    ,login_password_field_caption     ("Пароль")

    ,login_submit_button_caption      ("Войти"
            ,"Кіру")

    ,login_help_label(
            "Если Вы впервые заходите в приложение, наберите комбинацию <strong>*245#ОК</strong>, либо отправьте любое непустое сообщение на номер <strong>245</strong>. Пароль будет выслан по SMS.<p> Техническая поддержка : <a href='tel:3030'><button>3030</button></a> Стоимость информационно-справочной услуги, оказываемой оператором Call Center - 0 тенге. <a href='https://activ.kz/ru/service/navigator/tree' target='_blank'>Подробнее </a>",
            "Егер қосымшаға алғаш кірсеңіз, <strong>*245#ОК</strong> комбинациясын теріңіз немесе <strong>245</strong> нөміріне кез келген бос емес хабарлама жолдаңыз.  Пароль SMS түрінде келеді.<p>  Техникалық қолдау     : <a href='tel:3030'><button>3030</button></a> Call Center операторы көрсететін анықтамалық-ақпараттық қызмет құны - 0 теңге. <a href='https://activ.kz/kk/service/navigator/tree' target='_blank'>Толығырақ </a>"
    )
/*
    ("Если Вы впервые заходите в приложение, наберите комбинацию <strong>*245#ОК</strong>, либо отправьте любое непустое сообщение на номер <strong>245</strong>. Пароль будет выслан по SMS." +
            "<p>Техническая поддержка:\n" +
//            "<br/><a href='mailto:Service.Desk@kcell.kz'>Service.Desk@kcell.kz</a>" +
//            "<br/><a class='button' href='tel:+77012114357'>+7 701 211 4357</a>" +
            "<br /><a href='mailto:Service.Desk@kcell.kz'><button>Service.Desk@kcell.kz</button></a>"+
            " <a href='tel:+77012114357'><button>+7 701 211 4357</button></a>"
            //          "<p><a href='tel:+77012114357'><input type='button' value='+7 701 211 4357'>111</input></a>"

            ,"Егер қосымшаға алғаш кірсеңіз, <strong>*245#ОК</strong> комбинациясын теріңіз немесе <strong>245</strong> нөміріне кез келген бос емес хабарлама жолдаңыз. Пароль SMS түрінде келеді." +
            "<p>Техникалық қолдау:" +
            "<br /><a href='mailto:Service.Desk@kcell.kz'><button>Service.Desk@kcell.kz</button></a>"+
            " <a href='tel:+77012114357'><button>+7 701 211 4357</button></a>"
    )
*/

    ,logout_title                     ("Выход"
            ,"Шығу")

    ,logout_submit_button_caption     ("Выйти"
            ,"Шығу")

    ,product_title                    ("Продукты"
            ,"Өнімдер")

    ,product_productCaption_label     ("Продукт"
            ,"Өнім")

    ,product_bonusCaption_label       ("Бонус")

    ,product_bonusButton_label (
            "Бонус %s ед.",
            "Бонус %s бірлік"
    )

    ,product_bonus_collumn            ("%s ед."
            ,"%s бірлік")

    ,product_subscribe_form_title     ("Запрос номера IVR на подключение услуги"
            ,"Қызметті қосуға IVR нөмірін сұрату")

    ,product_subscribe_form_msisdn_field_caption
            ("Номер абонента (Activ)"
                    ,"Абонент нөмірі (Activ)")

    ,product_subscribe_form_sharebonus_field_caption
            ("Вернуть абоненту бонусов"
                    ,"Вернуть абоненту бонусов", "Return bonus to subscriber")

    ,sharebonus_invalid("Возвратные бонусы должны быть положительным числом")
    ,sharebonus_exceeds_product_bonus("Возвратные бонусы превышают бонусы продукта")
    ,sharebonus_facility_denied("Услуга, \"Возвратные бонусы\", для вас не доступна")

    ,product_subscribe_form_submit_btn_caption
            ("Запросить номер IVR"
                    ,"IVR нөмірін сұрату")

    ,product_subscribe_form_descr_btn_caption
            ("Описание услуги"
                    ,"Қызметтің сипаттамасы")

    ,product_subscribe_form_bonus     ("Бонус за подключение: %s ед. "
            ,"Қосу бонусы: %s бірлік")

    ,product_subscribe_form_price_label_1
            ("Абонентская плата в сутки - %s ед. "
                    ,"Тәулігіне абоненттік төлем - %s бірлік.")

    ,product_subscribe_form_price_label
            ("Цена  за подключение пакета - %s ед."
                    ,"Топтаманы қосу құны - %s бірлік.")

    ,product_subscribe_form_MbPlusCostLabel_caption
            ("Тариф за 1 MB сверх включенного в услугу трафика %s ед."
                    ,"Қызметке қосылған трафик үстінен жұмсалған 1 MB тарифі %S бірлік.")

    ,product_subscribe_form_validate_msg1
            ("Введите номер (Activ)"
                    ,"Нөмірді енгізіңіз (Activ)")

    ,product_subscribe_form_validate_msg2
            ("Неверно ввели номер (Activ)"
                    ,"Нөмірді қате енгіздіңіз (Activ)")

    ,product_ivr_view_msisdn          ("Для номера: <b>%s</b>"
            ,"Нөмір: <b>%s</b>")

    ,product_ivr_view_bonus           ("Бонус: %s ед."
            ,"Бонус %s бірлік")

    ,product_ivr_view_sharebonus           ("Возвратный Бонус: %s ед."
            ,"Возвратный  Бонус %s бірлік")

    ,product_ivr_view_title           ("Информация для подключения"
            ,"Қосылуға арналған ақпарат")

    ,product_ivr_view_ivr             ("Короткий номер подключения: <strong>%d</strong>"
            ,"Қосылудың қысқа нөмірі: <strong>%d</strong>")

    ,product_ivr_view_release         ("Выдан: %s"
            ,"Берілген кезі: %s")

    ,product_ivr_view_reject_title    ("Подписка отклонена"
            ,"Жазылым өшірілді")

    ,product_ivr_view_info            ("Абонент должен набрать короткий номер подключения <strong>%s</strong> со своего телефона и следовать инструкции"
            ,"Абонентке қызметті өз телефонынан қосу үшін <strong>%s</strong> қысқа нөмірін теріп, нұсқаулықтарға жүгіну қажет.")

    ,product_ivr_view_other_dealer_gen_code (
            "IVR код ранее сгенерирован другим продавцом"
            ,"Бұл өнім бойынша IVR коды Сіз үшін басқа сатушымен жасалынған"
    )
    ,subscribe_log_titile             ("История"
            ,"Тарих")

    ,subscribe_log_product_caption    ("Продукт"
            ,"Өнім")

    ,subscribe_log_bonus_caption      ("Бонус")

    ,transfer_title                   ("Перевод бонусов"
            ,"Бонустарды аудару")

    ,transfer_msisdn_field_caption    ("Номер получателя (Activ)"
            ,"Алушы нөмірі (Activ)")

    ,transfer_sum_field_caption       ("Сумма"
            ,"Сомасы")

    ,transfer_confirm_field_caption   ("Перевести"
            ,"Аудару")

    ,transfer_notif_label             ("Перевод бонуса осуществляется без комиссии"
            ,"Бонус комиссиясыз аударылады")

    ,transfer_confirm_label           ("<center>Вы действительно хотите<br/>перевести %s ед.<br/>на номер<br/> %s ?</center>"
            ,"<center>Сіз шынымен де<br/> %s бірлікті<br/> %s<br/>нөміріне аударғыңыз келе ме?<center>")

    ,transfer_confirm_submit_btn_caption
            ("Перевести"
                    ,"Аудару")

    ,transfer_confirm_cancel_btn_caption
            ("Отменить"
                    ,"Бас тарту")

    ,transfer_reject_notify           ("Операция отменена"
            ,"Операция тоқтатылды")

    ,main_title                       ("<h3>Bonus <strong>Commander</strong></h3>")

    ,main_menu_product                ("Продукты"
            ,"Өнімдер")

    ,main_menu_subscribe_log          ("История"
            ,"Тарих")

    ,main_menu_transfer_bonus         ("Перевод бонусов"
            ,"Бонустарды аудару")

    ,main_menu_bonus_info             ("Бонусы"
            ,"Бонустар")

    ,main_menu_out                    ("Выход"
            ,"Шығу")

    ,main_no_bonus_label              ("Бонусы: --- ед."
            ,"Бонус: --- бірлік")

    ,main_bonus_label                 ("Бонусы: %10.2f ед."
            ,"Бонус: %10.2f бірлік")

    ,page_title                       ("Bonus Commander")

    ,main_menu_change_lang            ("Смена языка"
            ,"Тілді ауыстыру")

    ,company_page_title            ("Компании"
            ,"Тілді ауыстыру")

/*
    ,main_menu_change_lang            ("Смена языка< br/>Тілді ауыстыру"
                                      ,"Тілді ауыстыру< br/>Смена языка")
*/

    ,subscribe_log_state_unknow        ("неизвестное состояние"
            ,"белгісіз қалып")

    ,subscribe_log_state_reject        ("отклонено"
            ,"бас тартылды")

    ,measure                           ("ед."
            ,"бірлік")

    ,subscribe_log_bonus_collumn   (
            "+ %s ед. " ,
            "+ %s бірлік "
    )

    ,subscribe_log_share_bonus   (
            "<br /> - %2$s ед." ,
            "<br /> - %2$s бірлік"
    )

//    ,subscribe_log_bonus_collumn   (
//            "${0,,choice,0#+ {0} ед.|+ {0} ед. <br /> - {1} ед.}" ,
//            "+ {0} бірлік <br /> - {1} бірлік"
//    )



    ,helper_duration_day (
/*
            "<br /> %s ед. на день",
            "<br /> күніне %s бірлік"
*/
            " - %s ед. на день",
            " - күніне %s бірлік"
    ),

    helper_duration_month (
/*
            "<br /> %s ед. в месяц",
            "<br /> ай сайын %s бірлік"
*/
            " - %s ед. в месяц",
            " - ай сайын %s бірлік"
    ),

    helper_duration  (
/*
        "<br /> %s ед.",
        "<br /> %s бірлік"
*/
        " - %s ед.",
        " - %s бірлік"
    )

    , ivr_poll_is_empty("Пул, ivr кодов, для абонента, исчерпан.")

    , error_not_authenticated("Неверный пароль."
            ,"Пароль қате.")

    , error_not_authorized("Неверный номер телефона продавца, обратитесь к Вашему мерчендайзеру Activ."
            ,"Сатушының телефон нөмірі қате. Activ мерчендайзеріне хабарласыңыз.")

    ,error_invalid_brend               ("Номер не является Activ."
            ,"Нөмір Activ емес.")

    ,error_invalid_sum  (
            "Вы указали неверную сумму для перевода.",
            "Сіз аударылатын соманы қате көрсеттіңіз.",
            "You have entered an invalid amount."
    )

    ,error_invalid_data                ("Вы неверно заполнили данные."
            ,"Сіз деректерді қате толтырдыңыз.")

    ,error_try_subscribe_self          (
            "Дилер не может подписать самого себя",
            "Дилер өзіне өзі қол қоя алмайды.",
            "Dealer cannot subscribe himself"
    )

    ,similar_service_already_subscribed  (
        "Сервис получения кода IVR недоступен. Услуга была ранее подключена.",
        "IVR кодын алу сервисін пайдалану мүмкін емес. Қызмет бұрын қосылған.",
        "IVR Code service is not available. Service is already activated."
    )

    ,get_bonus_not_available            (
        "Вы не можете получить бонус за подключение услуги этому абоненту.",
        "Сіз осы абонентке қызметті қосқаныңыз үшін бонус ала алмайсыз.",
        "You are not eligible for bonus for activating service on this account."
    )

    ,error_internal                    ("Извините, произошл сбой. Повторите позже"
            ,"Кешіріңіз, қате шықты")

    ,error_internal_404                ("Извините, проблема со связью, повторите операцию через некоторое время"
            ,"Кешіріңіз, байланыста ақаулық шықты. Операцияны біраз уақыттан кейін қайталаңыз.")

    ,language_name                     ("Русский язык","Қазақ тілі")
    , language_name_kk("Қазақ тілі")
    , language_name_ru("Русский язык")

    // todo translate
    ,login_validate_msg_enter_msisdn          ("Введите логин"
            ,"Сатушы нөмірін енгізіңіз (Activ)")

    ,login_validate_msg_unvalid_msisdn        ("Неверно ввели номер продавца (Activ)"
            ,"Сіз сатушы нөмірін қате енгіздіңіз (Activ)")

    ,login_validate_msg_enter_password        ("Введите пароль"
            ,"Парольді енгізіңіз")

    ,transfer_notify_msg_transfer_successfull ("Перевод успешно осуществлен."
            ,"Аударым табысты жүргізілді.")

    ,transfer_validate_msg_enter_msisdn       ("Введите номер получателя (Activ)"
            ,"Алушы нөмірін енгізіңіз (Activ)")

    ,transfer_validate_msg_unvalid_msisdn     ("Неверно ввели номер получателя (Activ)"
            ,"Сіз алушы нөмірін қате енгіздіңіз (Activ)")

    ,transfer_validate_msg_check_sum          ("Вы указали неверную сумму для перевода."
            ,"Сіз аударым сомасын қате көрсеттіңіз.")

    ,transfer_request_bonus_caption           ("Обновить информацию о бонусах"
            ,"Бонустар туралы ақпаратты жаңарту")

    ,transfer_bonus_not_allowed           (
        "Перевод бонусов для вас запрещен",
        "Сізге бонустарды аударуға тыйым салынған.",
        "Bonus transfer is not allowed for you"
    )


    ,subscribe_log_from                       ("")
    ,subscribe_log_to                         ("")
    ,subscribe_log_submit                     ("")

    , bonuses_info_title                     ("Бонусы"
            ,"Бонустар")

    , bonuses_info_no_bonus_label             ("Бонусы: --- ед."
            ,"Бонус: --- бірлік")


    ,bonuses_info_bonus_label                 ("Бонусы: %10.2f ед."
            ,"Бонус: %10.2f бірлік")

    ,bonuses_request_bonus_caption           ("Обновить информацию о бонусах"
            ,"Бонустар туралы ақпаратты жаңарту")

    ,error_balance_not_found               (
            "Произошел сбой, повторите позже, либо обратитесь в Call Center по номеру 3030",
            "Ақау орын алды, кейінірек қайталаңыз немесе 3030 нөмірі бойынша Call Center-ге хабарласыңыз",
            "A network failure occurred, please try later or contact Call Center on 3030"
    )

    ,sessionExpiredCaption       ( "Сессия истекла"
            ,"Сессия уақыты аяқталды"
            ,"Session Expired")

    ,sessionExpiredMessage       ( "Обратите внимание на несохраненные данные. Для продолжения, нажмите на <u>ссылку</u> или нажмите клавишу ESC."
            ,"Сақталмаған деректерге назар аударыңыз. Жалғастыру үшін <u>сілтемені</u> басыңыз немесе ESC пернесін басыңыз."
            ,"Take note of any unsaved data, and <u>click here</u> or press ESC key to continue.")

    ,communicationErrorCaption   ( "Проблема со связью"
            ,"Байланыс ақаулығы"
            ,"Communication problem")

    ,communicationErrorMessage   ( "Обратите внимание на несохраненные данные. Для продолжения, нажмите на <u>ссылку</u> или нажмите клавишу ESC."
            ,"Сақталмаған деректерге назар аударыңыз. Жалғастыру үшін <u>сілтемені</u> басыңыз немесе ESC пернесін басыңыз."
            ,"Take note of any unsaved data, and <u>click here</u> or press ESC to continue.")

    ,authenticationErrorCaption  ( "Проблема с аутентификацией"
            ,"Аутентификация ақаулығы"
            ,"Authentication problem")

    ,authenticationErrorMessage  ( "Обратите внимание на несохраненные данные. Для продолжения, нажмите на <u>ссылку</u> или нажмите клавишу ESC."
            ,"Сақталмаған деректерге назар аударыңыз. Жалғастыру үшін <u>сілтемені</u> басыңыз немесе ESC пернесін басыңыз."
            ,"Take note of any unsaved data, and <u>click here</u> or press ESC to continue.")

    ,internalErrorCaption        ( "Внутреняя ошибка сервера"
            ,"Сервердің ішкі қатесі"
            ,"Internal error")

    ,internalErrorMessage        ( "Пожалуйста, сообщите об этом администратору. Обратите внимание на несохраненные данные. Для продолжения, нажмите на <u>ссылку</u> или нажмите клавишу ESC."
            ,"Бұл туралы администраторға хабарлаңыз. Сақталмаған деректерге назар аударыңыз. Жалғастыру үшін <u>сілтемені</u> басыңыз немесе ESC пернесін басыңыз."
            ,"Please notify the administrator.<br/>Take note of any unsaved data, and <u>click here</u> or press ESC to continue.")


    ,outOfSyncCaption            ( "Произошла рассинхронизация с сервером"
            ,"Сервермен сихнронизация бұзылды"
            ,"Out of sync")


    , remeber_password_checkbox    ("Запомнить пароль", "Парольді есте сақтау")

    ,outOfSyncMessage            ( "Приложение рассинхронизированно с сервером. Для продолжения, нажмите на <u>ссылку</u> или нажмите клавишу ESC."
            ,"Қосымшаның сервермен сихнронизациясы бұзылды.  Жалғастыру үшін <u>сілтемені</u> басыңыз немесе ESC пернесін басыңыз."
            ,"Something has caused us to be out of sync with the server.<br/>Take note of any unsaved data, and <u>click here</u> or press ESC to re-sync.")


    ,cookiesDisabledCaption      ( "Cookies отключены"
            ,"Cookies өшірілді"
            ,"Cookies disabled")

    ,error_transfer_bonus_is_not_allowed
            ("Перевод бонусов вам запрещен.")


    ,cookiesDisabledMessage      ( "Для работы приложения требуются сookies. Включите сookies в вашем браузере и нажмите на <u>ссылку</u> или нажмите клавишу ESC."
            ,"Қосымша жұмысы үшін сookies қажет. Браузеріңізде сookies қосып, <u>сілтемені</u> басыңыз немесе ESC пернесін басыңыз."
            ,"This application requires cookies to function.<br/>Please enable cookies in your browser and <u>click here</u> or press ESC to try again.")

    ,unsupportedBrowser           (
            "<!DOCTYPE html>\n"
                    +"<html>\n"
                    +"<head lang=\"ru\">\n"
                    +"    <meta charset=\"UTF-8\">\n"
                    +"    <title></title>\n"
                    +"</head>\n"
                    +"<body>"
                    +"<h1>Извините, но ваш браузер не подходит.</h1>\n"
                    +"Версия вашего браузера устарела и не поддерживается.\n"
                    +"<br />Вам необходимо обновить браузер.\n"
                    +"<br />Рекомендуемые браузеры: Chrome, Firefox"
//            +" , Opera "
                    +" и Safari.\n"
                    +"<p />\n"
                    +"<p /><button onclick=\"location.href='https://www.google.com/chrome  '\">Установить Chrome </button>\n"
                    +"<p /><button onclick=\"location.href='http://www.mozilla.com/firefox '\">Установить Firefox</button>\n"
                    //        +"<p /><button onclick=\"location.href='http://www.opera.com/browser   '\">Установить Opera  </button>\n"
                    +"<p /><button onclick=\"lo cation.href='http://www.apple.com/safari    '\">Установить Safari </button>\n"
                    +"<p />Установите один из рекомендуемых браузеров, либо обновите до последней версии.\n"
                    +"<p /><button onclick=\"document.cookie='%s' ;window.location.reload();return false;\">Продолжить без обновления</button> (не рекомендуется)\n"
                    +"</body>"
                    +"</html>"

            ,

            "<!DOCTYPE html>\n"
                    +"<html>\n"
                    +"<head lang=\"kk\">\n"
                    +"    <meta charset=\"UTF-8\">\n"
                    +"    <title></title>\n"
                    +"</head>\n"
                    +"<h1>Кешіріңіз, браузеріңіз жарамайды.</h1>\n"
                    +"Браузеріңіздің нұсқасы ескірген және қабылданбайды.\n"
                    +"<br />Сізге браузеріңізді жаңарту қажет.\n"
                    +"<br />Ұсынылатын браузерлер: Chrome, Firefox"
//            +", Opera "
                    + "және Safari.\n"
                    +"<p />\n"
                    +"<p /><button onclick=\"location.href='https://www.google.com/chrome  '\"> Chrome  орнату</button>\n"
                    +"<p /><button onclick=\"location.href='http://www.mozilla.com/firefox '\"> Firefox орнату</button>\n"
                    //          +"<p /><button onclick=\"location.href='http://www.opera.com/browser   '\"> Opera   орнату</button>\n"
                    +"<p /><button onclick=\"location.href='http://www.apple.com/safari    '\"> Safari  орнату</button>\n"
                    +"<p />\n"
                    +"<p />Ұсынылған браузерлердің бірін орнатыңыз немесе соңғы нұсқасына дейін жаңартыңыз.\n"
                    +"<p /><button onclick=\"document.cookie='%s' ;window.location.reload();return false;\">Жаңартусыз жалғастыру</button> (бұлай істемеген жөн)\n"
                    +"</body>"
                    +"</html>"
    ),

    sms_add_bonus(
            "Вам начислено %s бонусных единиц за продажи.",
            "Сізге сауда жасағаныңыз үшін %s бонустық бірлік берілді",
            "You get %s units as your sales bonus"
    ),
    sms_tomato_subscribed(
            "Вам недоступно подключение данного тарифного плана или услуги",
            "Сіз осы тарифтік жоспарды немесе қызметті қоса алмайсыз"
    ),
    sms_success_subscribed(
            "Вы успешно подключили услугу или тариф"
    ),
    sms_service_from_same_group_already_subscribed(
            "Аналогичный тарифный план или услуга уже подключены",
            "Сізде осы тарифтік жоспар немесе қызмет қосылған"
    ),
    sms_not_enough_money_in_the_account(
            "У Вас недостаточно средств для подключения услуги. Чтобы узнать баланс, наберите *111#",
            "Сізде қызметті қосу үшін қаражат жеткіліксіз. Теңгерімді білу үшін *111# теріңіз"
    ),
    sms_internal_error(
         "Извините, произошла ошибка, повторите попытку через некоторое время либо обратитесь к дилеру или позвоните колцентр по номер 3030"
        ,"Кешіріңіз, қате шықты. Сатушыға хабарласыңыз немесе 3030 нөмірі арқылы колл-орталыққа қоңырау шалыңыз."
    ),
    sms_billing_error(
         "Извините, произошла ошибка, повторите попытку через некоторое время либо обратитесь к дилеру или позвоните колцентр по номер 3030"
            ,"Кешіріңіз, қате шықты. Сатушыға хабарласыңыз немесе 3030 нөмірі арқылы колл-орталыққа қоңырау шалыңыз."
    )
    , sms_product_not_available(
         "Вам недоступно подключение данного тарифного плана или услуги."
        ,"Сізге осы тарифтік жоспарды немесе қызметті қосу мүмкін емес"
    )

    // TODO translate
    ,dealer_get_bonus("На Ваш баланс поступило %s единиц.")

    , ussd_ivr_sms(
        "Для подключения услуги '%s' абоненту %s нужно набрать короткий номер %s со своего телефона и следовать инструкции. Номер действителен до %s",
        "'%s' қызметін қосу үшін %s абоненті өз телефонынан %s қысқа нөмірін теріп, нұсқауларды орындау қажет. Нөмір %s дейін жарамды.",
        "To activate ‘%s' service, customer %s must dial %s on their phone  and follow the prompts. Number is valid till %s"
    )

    ,ussd_bonus_info (
        "Бонус %s ед.",
        "Бонус %s бірлік",
        "Bonus - %s calling units"
    )

    , ussd_invalid_ivr_subsriber_msisdn(
        "Вы ввели неверный номер абонента '%s'",
        "Сіз абоненттің қате нөмірін тердіңіз '%s'",
        "You have entered a wrong number for '%s'"
    )

    , ussd_invalid_transfer_target_msisdn(
        "Вы ввели неверный номер получателя '%s'",
        "Сіз алушының қате нөмірін тердіңіз '%s'",
        "You have entered a wrong recipient number '%s'"
    )

    , ussd_transfer_order_created (
        "Заявка на перевод суммы %s для %s принята. Уведомление придет получателю.",
        "%s үшін %s соманы аударуға өтінім қабылданды. Хабарлама алушыға келеді.",
        "Request for transfer of %s to %s has been accepted. Recipient will get a notification."
    )

    , ussd_error_product_not_allowed(
        "Данная услуга не доступна Вам для продажи",
        "Бұл қызмет Сізге сату үшін қолжетімсіз.",
        "Sorry, this service is not available to you."
    )

    , ussd_error_product_not_exist(
        "Вы ввели неверный номер услуги, услуга не найдена",
        "Сіз қызметтің қате нөмірін тердіңіз, қызмет табылмады.",
        "You have entered an invalid service code, service not found"
    )


    ,ussd_error_invalid_brend  (
            "Номер не является Activ.",
            "Нөмір Activ емес.",
            "This is not a activ number."
    )

    ,ussd_transfer_invalid_brend  (
        "Номер получателя не является Activ",
        "Алушының нөмірі Activ емес.",
        "Recipient is not a activ subscriber."
    )

    ,ussd_ivr_invalid_brend  (
        "Номер абонента не является Activ",
        "Абоненттің нөмірі Activ емес.",
        "This is not a activ number."
    )


    ,ussd_error_invalid_sum  (
        error_invalid_sum._ru,
        error_invalid_sum._kk,
        error_invalid_sum._en
    )

    ,ussd_transfer_bonus_not_allowed (
        transfer_bonus_not_allowed._ru,
        transfer_bonus_not_allowed._kk,
        transfer_bonus_not_allowed._en
    )

    ,ussd_error_balance_not_found    (
        error_balance_not_found._ru,
        error_balance_not_found._kk,
        error_balance_not_found._en
    )

    ,ussd_error_try_subscribe_self (
        error_try_subscribe_self._ru,
        error_try_subscribe_self._kk,
        error_try_subscribe_self._en
    )

    ,ussd_similar_service_already_subscribed  (
        similar_service_already_subscribed._ru,
        similar_service_already_subscribed._kk,
        similar_service_already_subscribed._en
    )

    ,ussd_get_bonus_not_available (
        get_bonus_not_available._ru,
        get_bonus_not_available._kk,
        get_bonus_not_available._en
    )

    ,ussd_error_internal (
            "Произошел сбой, повторите позже, либо обратитесь в Call Center по номеру 3030",
            "Ақау орын алды, кейінірек қайталаңыз немесе 3030 нөмірі бойынша Call Center-ге хабарласыңыз",
            "A network failure occurred, please try later or contact Call Center on 3030"
    )

    ,ussd_error_internal_404(
            "Произошел сбой, повторите позже, либо обратитесь в Call Center по номеру 3030",
            "Ақау орын алды, кейінірек қайталаңыз немесе 3030 нөмірі бойынша Call Center-ге хабарласыңыз",
            "A network failure occurred, please try later or contact Call Center on 3030"
    ),

    captcha_prompt(
            "Введите текст, который Вы видите на картинке:",
            "Суретте көріп тұрған мәтінді енгізіңіз:"
    )

    ;

    private String _ru;
    private String _kk;
    private String _en;

    SpmotResourceBundle() {

    }

    SpmotResourceBundle(String _ru) {
        this._ru = _ru;
        this._kk = _ru;
        this._en = _ru;
    }

    SpmotResourceBundle(String _ru, String _kk) {
        this._ru = _ru;
        this._kk = _kk;
        this._en = _ru;
    }

    SpmotResourceBundle(String _ru, String _kk, String _en) {
        this._ru = _ru;
        this._kk = _kk;
        this._en = _en;
    }

    void set(String _ru) {
        this._ru = _ru;
        this._kk = _ru;
        this._en = _ru;
    }

    void set(String _ru, String _kk) {
        this._ru = _ru;
        this._kk = _kk;
        this._en = _ru;
    }

    void set(String _ru, String _kk, String _en) {
        this._ru = _ru;
        this._kk = _kk;
        this._en = _en;
    }

    public String _ru() {
        return _ru;
    }

    public String _kk() {
        return _kk;
    }

    public String _en() {
        return _en;
    }

    @Override
    public void setValue(Language language, String value) {
        switch (language) {
            case KZ:
            case KK: _kk = value; break;
            case RU: _ru = value; break;
            case EN: _en = value; break;
            default: _ru = value;
        }
    }



    public String getKk() {return  _kk ;}
    public String getKz() {return  _kk ;}
    public String getRu() {return  _ru ;}
    public String getEn() {return  _en ;}
    public void setKk(String val) {_kk = val;}
    public void setKz(String val) {_kk = val;}
    public void setRu(String val) {_ru = val;}
    public void setEn(String val) {_en = val;}

}

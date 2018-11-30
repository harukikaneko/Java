 
    // 入力内容チェックのための関数
    function input_check(){
    var result = true;
 
    // エラー用装飾のためのクラスリセット
 
    $('#code').removeClass("inp_error");
    $('#search_name').removeClass("inp_error");
    $('#pass').removeClass("inp_error");
    $('#user_id').removeClass("inp_error");
 
    // 入力エラー文をリセット
    $("#code").empty();
    $("#search_name").empty();
    $("#pass_error").empty();
    $("#user_id_error").empty();
 
    // 入力内容セット
    var code = $("#code").val();
    var search_mame = $("#search_mame").val();
    var pass  = $("#pass").val();
    var user_id  = $("#user_id").val();
 
   
    // 検索（コード）
    if(code.length > 20){
        $("#code_error").html("<i class='fa fa-exclamation-circle'></i> 銘柄コードは20文字以内入力してください。");
        $("#code").addClass("inp_error");
        result = false;
    }else if(!code.match(/^[a-zA-Z0-9]+$/))
    	$("#code_error").html("<i class='fa fa-exclamation-circle'></i> 銘柄コードは半角数字で入力してください。");
        $("#code").addClass("inp_error");
        result = false;
    }
    // 検索（文字）
    if(!search_name.match(/^([a-zA-Z0-9])+([ァ-ロワヲンー 　\r\n\t])+([ぁ-ろわをんー 　\r\n\t]))+$/)){
        $('#search_name').html("<i class='fa fa-exclamation-circle'></i> 正しい文字を入力してください。");
        $("#search_name").addClass("inp_error");
        result = false;
    }else if(search_name.length > 20){
        $('#search_name_error').html("<i class='fa fa-exclamation-circle'></i> 該当する項目がありません。");
        $("#search_name").addClass("inp_error");
        result = false;
    }
    // パスワード
    if(tel == ""){
        $("#pass_error").html("<i class='fa fa-exclamation-circle'></i> パスワードは必須です。");
        $("#pass").addClass("inp_error");
    result = false;
    }else if((!pass.match(/^[a-zA-Z0-9]+$/)) || (pass.length > 25 )){
        $('#pass_error').html("<i class='fa fa-exclamation-circle'></i> 正しいパスワードを入力してください。");
        $("#pass").addClass("inp_error");
        result = false;
    }
    // ログインID 
    if (user_id == ""){
        $("#user_id_error").html("<i class='fa fa-exclamation-circle'></i> ログインIDが入力されていません");
        $("#user_id").addClass("inp_error");
        result = false;
    }else if(user_id.match(user_id.length < 25 )){
        $('#user_id_error').html("<i class='fa fa-exclamation-circle'></i> 正しいログインIDを入力してください。");
        $("#user_id").addClass("inp_error");
        result = false;
    }else if(user_id.match(/^[ 　\r\n\t]*$/)){
        $('#user_id_error').html("<i class='fa fa-exclamation-circle'></i> ログインIDは必須です。");
        $("#user_id").addClass("inp_error");
        result = false;
    }
 

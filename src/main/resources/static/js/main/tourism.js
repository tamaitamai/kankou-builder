function prefectureSelect(prefecture){
        var postData={
            prefecture: prefecture
        }

        $.ajax({
            type: 'post',
            url: '/tourism/prefectureSelect',
            data: postData,
            success: function(response){
                $('.tourism-list').empty();
                for(let i=0;i<response.length;i++){                    
                    var tourismImage=$('<img>').attr('src','/image/'+response[i].image)
                    .addClass('tourism-image').attr('tourism-id',response[i].id); 
                    var tourismName=$('<div>').text(response[i].name).addClass('tourism-name');
                    var tourism=$('<div>').addClass('tourism').append(tourismImage).append(tourismName);
                    $('.tourism-list').append(tourism);
                }
            }
        });
}

$(function(){
    //ページ更新時すべての観光地の表示
    prefectureSelect("");

    //観光地の県を絞り込む
    $('.prefecture-select').change(function(){
        var prefecture=$(this).val();
        prefectureSelect(prefecture);
    })

    //写真をクリックしたときに詳細画面に遷移
    $(document).on('click','.tourism-image',function(){
        var tourismId=$(this).attr('tourism-id');//選択した観光地id
        var form=$('<form>',{'action': '/tourism/tourismDetail','method': 'post'}).addClass('form');        
        var submitId=$('<input>',{'type': 'hidden','name': 'id','value': tourismId});//送信するidを判別
        $(document.body).append(form);        
        $('.form').append(submitId);
        form.submit();
    })
})
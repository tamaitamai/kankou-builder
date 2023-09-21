$(function(){
    //星の数を設定
    var rank=$('.review-rank-value').val();
    for(let i=0;i<=rank-1;i++){
        $('.review-star').eq(i).text('★');
        $('.review-star').eq(i).css('color','rgb(245, 245, 19)');
    }

    //クリックした星の数になる
    $('.review-star').click(function(){
        $('.review-star').text('☆');
        $('.review-star').css('color','black');
        var reviewLength=$('.review-star').index(this);
        for(let i=0;i<=reviewLength;i++){
            $('.review-star').eq(i).text('★');
            $('.review-star').eq(i).css('color','rgb(245, 245, 19)');
        }
        $('.review-rank-value').val(reviewLength+1);
    })
})
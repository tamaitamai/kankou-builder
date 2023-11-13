$(function(){
    //評価の星の数を表示
    var reviewLength=$('.review-box').length;
    for(let i=0;i<reviewLength;i++){
        var rankLength=$('.review-rank-value').eq(i).val();
        let star='';
        for(let j=0;j<rankLength;j++){
            star+='★';
        }
        $('.review-rank').eq(i).text(star);
    }

    if(isNaN($('.average-rank').text())){
        $('.average-star').text('☆');
        $('.average-star').css('color','black');
        $('.average-rank').text('評価なし');
    }
})
$('document').ready(function (){
    $('#gride tbody tr').each(function(key){
        var classe = $(this).attr('class');
        $(this).hover(
            function(){
                $(this).removeAttr('class').attr('class','gradeHover');
                if(classe == 'gradeUnnabled')
                    $(this).removeAttr('class').addClass('gradeHover').css({'font-style':'italic'});
            },
            function(){
                $(this).removeClass('gradeHover').addClass(classe);
            }                
        );
    });
});
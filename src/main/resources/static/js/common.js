 $('[name="locales"]').change(function () {
             var selectedOption = $('[name="locales"]').val();
             console.log(selectedOption);
             if (selectedOption != ''){
                 window.location.replace('?lang=' + selectedOption);
             }
  });

  var resetValidation = function(){
      $( "span" ).each(function(){
          $(this).removeClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" );
          $(this).parents(".form-group" ).removeClass( "has-success" ).removeClass( "has-error" );
      });
      $("em").each(function(){$(this).hide()});
   }

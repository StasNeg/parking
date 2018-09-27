 $('[name="locales"]').change(function () {
             var selectedOption = $('[name="locales"]').val();
//             console.log(selectedOption);
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
  var dataTableI18n = {'uk':'//cdn.datatables.net/plug-ins/1.10.19/i18n/Ukrainian.json',
                        'ru':'//cdn.datatables.net/plug-ins/1.10.19/i18n/Russian.json',
                        'en':'//cdn.datatables.net/plug-ins/1.10.19/i18n/English.json'};
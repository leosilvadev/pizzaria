$(document).ready(function(){
	
	aplicarListeners();
	
	aplicatListenerBtnSalvar();
	
});

var limparModal = function(){
	$('#id').val('');
	$('#nome').val('');
	$('#preco').val('');
	$('#categoria').val('');
	$('#ingredientes option').attr('selected', false);
};

var aplicatListenerBtnSalvar = function(){
	$('#btn-salvar').on('click', function(){
		var url = 'pizzas';
		var dadosIngrediente = $('#form-pizza').serialize();
		$.post(url, dadosIngrediente)
			.done(function(pagina){
				$('#secao-pizzas').html(pagina)
				aplicarListeners();
				
			})
			.fail(function(){
				alert('Erro ao salvar!');
				
			})
			.always(function(){
				$('#modal-pizza').modal('hide');
			});
	});
}

var aplicarListeners = function(){
	
	$('#modal-pizza').on('hide.bs.modal', limparModal);
	
	$('.btn-deletar').on('click', function(){
		var pizzaId = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();
		
		$.ajax({
			url : 'pizzas/'+pizzaId,
			type: 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
		    success: function() {
		    	$('tr[data-id="'+pizzaId+'"]').remove();
				var pizzas = parseInt( $('#quantidade-pizzas').text() );
		    	$('#quantidade-pizzas').text(pizzas - 1);
		    }
		});
		
	});
	
	$('.btn-editar').on('click', function(){
		var pizzaId = $(this).parents('tr').data('id');
		var url = 'pizzas/'+pizzaId;
		$.get(url)
			.success(function(pizza){
				$('#id').val(pizza.id);
				$('#nome').val(pizza.nome);
				$('#preco').val(pizza.preco);
				$('#categoria').val(pizza.categoria);
				
				pizza.ingredientes.forEach(function(ingrediente){
					var id = ingrediente.id;
					$('#ingredientes option[value='+id+']').attr('selected', true);
				});
				
				$('#modal-pizza').modal('show');
			});;
	});
	
};
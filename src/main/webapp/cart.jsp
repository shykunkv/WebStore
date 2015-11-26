{% extends "catalog.html" %}

{% load catalog_filters %}

{% block content %}
<style>
				.quantity {
					color:black;
				}
				.cart {
					width: 200px;
				}
				th, td, tr {
					padding: 5px;	
				}

</style>


	<table class = "cart">
		<caption><h3>{% if request.session.lang = 'en-us'%} Your shopping cart {% else %} Ваша корзина {% endif %}</h3></caption>
		<thead>
			<tr>
				<th scope = "col">{% if request.session.lang = 'en-us'%} Product {% else %} Продукция {% endif %}	</th>
				<th scope = "col">{% if request.session.lang = 'en-us'%} Price {% else %} Цена {% endif %}	</th>
				<th></th>
				<th></th>
				<th></th>
				<th scope = "col">{% if request.session.lang = 'en-us'%} Total {% else %} Всего {% endif %}	</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th colspan = "5">{% if request.session.lang = 'en-us'%} Cart Subtotal: {% else %} Всего к расчету: {% endif %}	</th>
				<th> {{ cart_subtotal }} </th>
			</tr>
			{% if cart_items %}
			<tr>
				<th colspan = "6"> <a href = "{% url 'checkout' %}" class="btn btn-default">{% if request.session.lang = 'en-us'%} Checkout now {% else %} Оплатить {% endif %}	</a></th>	
			</tr>
			{% endif %}	
		</tfoot>
		<tbody>
			{% if cart_items %}
				{% for item in cart_items %}
					<tr>
						<td>
							<a href = "{{ item.get_absolute_url }}"> {{ item.name }} </a>
						</td>
						<td>{{ item.price  }}</td>
						<td>
							<form method = "POST" action = "." class = "cart">
								<label for = "quantity">{% if request.session.lang = 'en-us'%} Quantity: {% else %} Количество: {% endif %}	</label>
								<input type = "text" name = "quantity" value = "{{ item.quantity }}" id = "quantity" size = "2" class = "quantity" maxlength = "5" />
								<input type = "hidden" name = "item_id" value = "{{ item.id }}" />
						</td>
						<td>
							{% if request.session.lang = 'en-us'%}
								<input type = "submit" name = "submit" value = "Update" class="btn btn-default" />
							{% else %}
								<input type = "submit" name = "submit" value = "Обновить" class="btn btn-default" />
							{% endif %}				
							</form>
						</td>
						<td>
							<form method = "POST" action = "." class = "cart">
								<input type = "hidden" name = "item_id" value = "{{ item.id }}" />
								{% if request.session.lang = 'en-us'%}
									<input type = "submit" name = "submit" value = "Remove" class="btn btn-default" />
								{% else %}
									<input type = "submit" name = "submit" value = "Удалить" class="btn btn-default" />
								{% endif %}

							</form>	
						</td>
						<td>{{ item.total  }}</td>
					</tr>	
					{% endfor %}
				{% else %}
				<tr>
					<td colspan = "6">{% if request.session.lang = 'en-us'%} Your cart is empty {% else %} Ваша корзина пуста {% endif %}	</td>
				</tr>
				{% endif %}	
		</tbody>
	</table>

{% endblock %}
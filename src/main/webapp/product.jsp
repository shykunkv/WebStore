{% extends "catalog.html" %}

{% block name %}
	{{ p.name }}
{% endblock %}


{% block products %}
	<div class = "col-xs-9">
		<div class = "col-xs-7">
			<img src = "{{ p.image.url }}" alt = "{{ p.name }}" />
		</div>
		<div class = "col-xs-5">
			<p>{% if request.session.lang = 'en-us'%}  Name: {% else %} Название: {% endif %}{{ p.name }} </p>
			<p>{% if request.session.lang = 'en-us'%} Brand: {% else %} Производитель: {% endif %}<em> {{ p.brand }} </em> </p>
			<p>
				{% if request.session.lang = 'en-us'%} In categories: {% else %} В категориях: {% endif %}
				{% for c in categories %}
					<a href = "{{ c.get_absolute_url }}">
						{% if request.session.lang = 'en-us'%}
							{{ c.name }}
						{% else %}
							{{ c.r_name }}
						{% endif %}
					</a>
					{% if not forloop.last %} , {% endif %}
				{% endfor %}
			</p>
			<p>{% if request.session.lang = 'en-us'%}  Price: {% else %} Цена: {% endif %}$ {{ p.price }} </p>
			<br/>
			<style>
				.quantity {
					color:black;
				}
				.errorlist {
					color:red;
				}
			</style>
			{% if user.is_authenticated %}
			<form method = "POST" action = "." class = "cart">	
				{{ form.as_p }}
				{% if request.session.lang = 'en-us'%}
					<input type = "submit" value = "Add to Cart" name = "submit" alt = "Add To Cart" class="btn btn-default"/>
				{% else %}
					<input type = "submit" value = "В корзину" name = "submit" alt = "Add To Cart" class="btn btn-default"/>
				{% endif %}
			</form>
			{% endif %}
			<br/>
			<br/>
		</div>
			
		<h3>{% if request.session.lang = 'en-us'%} Product description: {% else %} Описание продукта: {% endif %}</h3>
		<p>
			{% if request.session.lang = 'en-us'%}
				{{ p.description }}
			{% else %}
				{{ p.r_description }}
			{% endif %}
		</p>
	</div>
{% endblock %}
{% extends "catalog.html" %}

{% block content %}
	<h3>
		{% if request.session.lang = 'en-us'%} 
			Thanks for using Django Shop
		{% else %}
			Спасибо за использование нашего магазина
		{% endif %}	
	</h3>
{% endblock %}
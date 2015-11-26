{% extends "catalog.html" %}

{% block content %}
	<h3>
		{% if request.session.lang = 'en-us'%} 
			Sorry, you're in Black List and can't checkout
		{% else %}
			Извините, Вы в черном списке и не можете делать покупки
		{% endif %}
	</h3>
	{% if request.session.lang = 'en-us'%} 
		Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
	{% else %}
		Тут должен быть какой-то текст, описывающий ошибку...
	{% endif %}
{% endblock %}
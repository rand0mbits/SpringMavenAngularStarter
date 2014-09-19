
<%--<div x-ng-repeat="message in messages()" class="{{bgClass[message.level]}} message">
	{{message.value}}
	<button type="button" class="close close-note" x-dismiss="alert" x-ng-click="removeMessage(message)"><span aria-hidden="true">&times;</span></button>
</div>
 --%>

<div x-ng-repeat="messageBlock in errors()" class="msg-block msg-errors">
	<p x-ng-if="messageBlock.length == 1">
		<span class="short-message">{{messageBlock[0].value}}</span>
		<button type="button" class="close msg-close" x-ng-click="removeMessage(messageBlock[0])"><span aria-hidden="true">&times;</span></button>
	</p>
	<div x-ng-if="messageBlock.length > 1">
		<p>Error:
			<button type="button" class="close msg-close" x-ng-click="removeMessage(messageBlock)"><span aria-hidden="true">&times;</span></button>
		</p>
		<ul>
			<li x-ng-repeat="message in messageBlock">
				{{message.value}}
			</li>
		</ul>
	</div>
</div>

<div x-ng-repeat="messageBlock in warnings()" class="msg-block msg-warnings">
	<p x-ng-if="messageBlock.length == 1">
		<span class="short-message">{{messageBlock[0].value}}</span>
		<button type="button" class="close msg-close" x-ng-click="removeMessage(messageBlock[0])"><span aria-hidden="true">&times;</span></button>
	</p>
	<div x-ng-if="messageBlock.length > 1">
		<p>Warning:
			<button type="button" class="close msg-close" x-ng-click="removeMessage(messageBlock)"><span aria-hidden="true">&times;</span></button>
		</p>
		<ul>
			<li x-ng-repeat="message in messageBlock">
				{{message.value}}
			</li>
		</ul>
	</div>
</div>

<div x-ng-repeat="messageBlock in successes()" class="msg-block msg-successes">
	<p x-ng-if="messageBlock.length == 1">
		<span class="short-message">{{messageBlock[0].value}}</span>
		<button type="button" class="close msg-close" x-ng-click="removeMessage(messageBlock[0])"><span aria-hidden="true">&times;</span></button>
	</p>
	<div x-ng-if="messageBlock.length > 1">
		<p>Success:
			<button type="button" class="close msg-close" x-ng-click="removeMessage(messageBlock)"><span aria-hidden="true">&times;</span></button>
		</p>
		<ul>
			<li x-ng-repeat="message in messageBlock">
				{{message.value}}
			</li>
		</ul>
	</div>
</div>
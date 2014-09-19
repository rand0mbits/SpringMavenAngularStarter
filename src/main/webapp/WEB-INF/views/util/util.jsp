<h1 class="page-header">MyProject Controls and Style Guide</h1>

<div id="utils-container">
	<section>
		<div class="button-row text-center">
			This is a <button class="btn btn-primary btn-sm" x-ng-click="openModal()">Button</button> row.
		</div>
	</section>
	<div class="container-fluid" style="margin-bottom:200px;">
		
		<div class="row">
			<h3>Messages</h3>
			Type: 
			<select x-ng-model="messages.newMessageType">
				<option value="info">Success</option>
				<option value="warning">Warning</option>
				<option value="error">Error</option>
				<option value="prompt">Prompt</option>
			</select>
			Text: <input type="text" x-ng-model="messages.newMessageText"/>
			<button x-ng-click="messages.addNewMessage()">Add</button>
			<button x-ng-click="messages.submitMessages()">Submit</button>
			<button x-ng-click="messages.messages = []">Clear</button>
			<ul><li x-ng-repeat="message in messages.messages">{{message.level}}: "{{message.value}}"</li></ul>
		</div>
	</div>
</div>
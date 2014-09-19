var MessageService = function ($timeout) {
	return {
		http: undefined,
		modal: undefined,
		messages: [],
		successCollections: [],
		warningCollections: [],
		errorCollections: [],
		fadeTimeout: 5000,
		addMessages: function(messages) {
			var service = this;
			if (!angular.isArray(messages)) {
				return;
			}
			// split messages into different message types
			var errors = [], warnings = [], successes = [], prompt = undefined;
			for (var i = 0; i < messages.length; i++) {
				var message = messages[i];
				switch (message.level) {
				case 'info': successes.push(message); break;
				case 'error': errors.push(message); break;
				case 'warning': warnings.push(message); break;
				case 'prompt': prompt = message; break;
				}
				if (!angular.isUndefined(prompt)) {
					break;
				}
			}
			
			// if we've got a prompt, just display the prompt and disregard everything else
			if (!angular.isUndefined(prompt)) {
				var answer = confirm(prompt.value);
				if (answer) {
					response.config.confirmMethod();
				}
				return;
			}
			else {
				if (successes.length > 0) service.successCollections.push(successes);
				if (errors.length > 0) service.errorCollections.push(errors);
				if (warnings.length > 0) service.warningCollections.push(warnings);
			}
			
//			service.messages = service.messages.concat(messages);
			// timeout only info messages
//			var infoMessages = [];
//			for (var i = 0; i < messages.length; i++) {
//				if (messages[i].level == 'info') {
//					infoMessages.push(messages[i]);
//				}
//			}
			if (successes.length > 0) {
				$timeout(function() {
					service.removeMessages(successes);
				}, service.fadeTimeout);
			}
			
		},
		// expects an array. to remove a single message pass [message]
		removeMessages: function(messages) {
			var service = this;
			for (var i = 0; i < messages.length; i++) {
				var idx = service.messages.indexOf(messages[i]);
				if (idx >= 0) {
					service.messages.splice(idx, 1);
				}
			}
			// remove from collections
			function removeFromCollection(message, collection) {
				for (var i = 0; i < collection.length; i++) {
					var idx = collection[i].indexOf(message);
					if (idx >= 0) {
						collection[i].splice(idx, 1);
						if (collection[i].length < 1) {
							collection.splice(i, 1);
						}
					}
				}
			}
			// make a copy of messages array so that we're not splicing from it
			var messagesCopy = []; for (var i = 0; i < messages.length; i++) messagesCopy.push(messages[i]);
			for (var i = 0; i < messagesCopy.length; i++) {
				var message = messagesCopy[i];
				switch(message.level) {
				case 'info': removeFromCollection(message, service.successCollections); break;
				case 'error': removeFromCollection(message, service.errorCollections); break;
				case 'warning': removeFromCollection(message, service.warningCollections); break;
				}
			}
		},
		addMessage: function(message, type) {
			this.addMessages([{level: type, value: message}]);
		},
		addInfo: function(message) {
			this.addMessage(message, 'info');
		},
		addError: function(message) {
			this.addMessage(message, 'error');
		},
		getMessages: function(response) {
			var service = this;
			this.http.get('messages/get-all', {
				omitRequestId: true,
				noSpinner: true,
			}).success(function(data) {
				if (angular.isArray(data) && data.length > 0) {
					//console.log('got messages from: ' + response.config.method + ':' + response.config.url);
					//console.log(data);
					// check if we have any prompts
					var prompts = [];
					for (var i = data.length - 1; i >= 0; i--) {
						var message = data[i];
						if (message.level === "prompt") {
							prompts.unshift(message);
							data.splice(i, 1);
						}
					}
					if (prompts.length == 0) {
						service.addMessages(data);	
					}
					else {
						var answer = confirm(prompts[0].value);
						if (answer) {
							response.config.confirmMethod();
						}
					}				
				}
				else {
					//console.log('no messages');
				}
			});
		}
	};
};
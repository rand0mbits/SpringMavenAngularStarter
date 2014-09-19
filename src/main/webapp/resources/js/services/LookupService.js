var LookupService = function ($http) {
	var lookups = {};
	var promise = $http.get('lookups/all').then(function(response) {
		angular.extend(lookups, response.data);
		console.log('lookups:', response.data);
	});
	return {
		data: lookups,
		promise: promise,
		// returns whole collection if no lk is passed, otherwise returns lk element in collection
		get: function(collection, lk) {
			if (lk) {
				if (collection in this.data && lk in this.data[collection]) {
					return this.data[collection][lk];
				}
				else return undefined;
			}
			else {
				return this.data[collection];
			}
		},
		getDescription: function(collection, lk) {
			return this.getField(collection, lk, 'description');
		},
		
		getShortDescription: function(collection, lk) {
			return this.getField(collection, lk, 'shortDescription');
		},
		
		getField: function(collection, lk, field) {
			var obj = this.get(collection, lk);
			return obj && obj[field];
		},
		
		getCurrentSeasonYearlyValue: function(leagueLk) {
			if (this.data && leagueLk && this.data.yearlySystemValuesByLeagueAndYear
					&& leagueLk in this.data.yearlySystemValuesByLeagueAndYear) {
				var valuesByYear = this.data.yearlySystemValuesByLeagueAndYear[leagueLk];
				var now = new Date();
				var prevCalendarYear = now.getFullYear() - 1;
				var nextCalendarYear = prevCalendarYear + 2;
				for (var year = prevCalendarYear; year <= nextCalendarYear; year++) {
					if (year in valuesByYear) {
						var startOfSeason = new Date(valuesByYear[year].firstDayOfSeason);
						var endOfSeason = new Date(valuesByYear[year].lastDayOfSeason);
						if (now >= startOfSeason && now <= endOfSeason) {
							return valuesByYear[year];
						}
					}
				}
				return undefined;
			}
			return undefined;
		}
	};
};
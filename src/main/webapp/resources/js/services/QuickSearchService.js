var QuickSearchService = function($http) {
	return {
		searchPlayers: function (val, personTypeLk) {
			if (!personTypeLk) {
				personTypeLk = 'PLYR';
			}
			return $http.get('player/qs', {
				params: {
					criteria: val,
					personTypeLk: personTypeLk,
				},
				noSpinner: true,
				omitRequestId: true,
			})
			.then(function (res) {
				return res.data;
			});
		},
		searchAgencies: function(searchString) {
			return $http.get('lookups/agencies/qs', {
				params: {
					criteria: searchString,
				},
				noSpinner: true,
				omitRequestId: true,
			})
			.then(function (res) {
				return res.data;
			});
		}
	};
};
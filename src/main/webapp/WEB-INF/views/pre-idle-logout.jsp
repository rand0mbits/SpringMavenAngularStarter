<div class="modal-header">
     <h3 class="modal-title">MyProject Inactivity warning.</h3>
</div>
<div class="modal-body">
    You will be logged out in {{model.remainingGracePeriod | date: 'mm:ss'}} due to inactivity. Click OK to remain logged in.
</div>
<div class="modal-footer">
    <button class="btn btn-primary" ng-click="model.ok()">OK</button>
</div>
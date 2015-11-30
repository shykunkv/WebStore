<div class="modal fade" id="add" role="dialog">
    <form action="/main" method="POST">
        <div class="modal-dialog">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="name">Modal Header</h4>
                </div>

                <div class="modal-body">
                    <input type="text" name="name" class="form-control" placeholder="Category name"/>
                    <br>
                    <textarea class="form-control" name="description" id="message-text" placeholder="Category description"></textarea>
                    <input type = "hidden" name = "action" value="addCategory"/>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-default">Save</button>
                </div>

            </div>

        </div>
    </form>
</div>

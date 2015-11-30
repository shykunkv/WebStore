

<div class="modal fade" id="edit" role="dialog">

    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="name1">Modal Header</h4>
            </div>

            <div class="modal-body">
                <form action="/main" method="POST">
                    <input type="text" name="name" class="name form-control" placeholder="Category name"/>
                    <br>
                    <textarea class="description form-control" name="description" id="message-text-edit" placeholder="Category description"></textarea>
                    <input type = "hidden" name = "action" value="editCategory"/>
                    <input class="old_name" type="hidden" name="old_name"/>;<button type="submit" class="btn btn-default">Save</button>
                </form>
            </div>

            <div class="modal-footer">

                <form action="/main" method="POST">
                    <input type="hidden" name="action" value="deleteCategory"/>
                    <input class="del_name" type="hidden" name="name"/>;
                    <button type="submit" class="btn btn-default">Delete</button>
                </form>
            </div>

        </div>

    </div>

</div>



<script type="text/javascript">
    $('#edit').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var name = button.data('name');
        var desc = button.data('description');

        var modal = $(this);

        modal.find('.name').val(name);
        modal.find('.old_name').val(name);
        modal.find('.del_name').val(name);
        modal.find('.description').val(desc);
    });
</script>
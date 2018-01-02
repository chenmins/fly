package tv.xza.fly

class ListController {

    def index() {
        if(params.id==null)
            return
        def id = params.id as Long
        params.offset = params.offset == null ? 0 : params.offset
        params.max = params.max == null ? 15 : params.max
        def list = Topic.findAll("from Topic as b where b.column.id = :id",
                [id:id],
                [max: params.max, offset: params.offset, sort: "id", order: "desc"])
        [list: list, count: Topic.countByColumn(Column.get(id))]
    }
}

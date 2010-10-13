package se.webinventions.test.artisteer

class Test2Controller {

   static navigation = [
		group:'artisteermainmenu2',
		order:11,
		title:'Test2',
        action:'list',
        subItems: ['list', 'create']

	]
  
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [test2InstanceList: Test2.list(params), test2InstanceTotal: Test2.count()]
    }

    def create = {
        def test2Instance = new Test2()
        test2Instance.properties = params
        return [test2Instance: test2Instance]
    }

    def save = {
        def test2Instance = new Test2(params)
        if (test2Instance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'test2.label', default: 'Test2'), test2Instance.id])}"
            redirect(action: "show", id: test2Instance.id)
        }
        else {
            render(view: "create", model: [test2Instance: test2Instance])
        }
    }

    def show = {
        def test2Instance = Test2.get(params.id)
        if (!test2Instance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'test2.label', default: 'Test2'), params.id])}"
            redirect(action: "list")
        }
        else {
            [test2Instance: test2Instance]
        }
    }

    def edit = {
        def test2Instance = Test2.get(params.id)
        if (!test2Instance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'test2.label', default: 'Test2'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [test2Instance: test2Instance]
        }
    }

    def update = {
        def test2Instance = Test2.get(params.id)
        if (test2Instance) {
            if (params.version) {
                def version = params.version.toLong()
                if (test2Instance.version > version) {
                    
                    test2Instance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'test2.label', default: 'Test2')] as Object[], "Another user has updated this Test2 while you were editing")
                    render(view: "edit", model: [test2Instance: test2Instance])
                    return
                }
            }
            test2Instance.properties = params
            if (!test2Instance.hasErrors() && test2Instance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'test2.label', default: 'Test2'), test2Instance.id])}"
                redirect(action: "show", id: test2Instance.id)
            }
            else {
                render(view: "edit", model: [test2Instance: test2Instance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'test2.label', default: 'Test2'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def test2Instance = Test2.get(params.id)
        if (test2Instance) {
            try {
                test2Instance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'test2.label', default: 'Test2'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'test2.label', default: 'Test2'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'test2.label', default: 'Test2'), params.id])}"
            redirect(action: "list")
        }
    }
}

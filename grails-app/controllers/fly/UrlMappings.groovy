package fly

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/real"(view:'/index')
        "/"(controller:"index", action:"index")
        "500"(view:'/error')
//        "500"(view:'/error/500')
//        "404"(view:'/notFound')
        "404"(view:'/error/404')
    }
}

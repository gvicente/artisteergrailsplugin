class UrlMappings {

	static mappings = {



     "/images/${imageId}-${size}.${type}" {
            controller = 'dbContainerImage'
            saction = 'index'
        }
    "/images/${imageId}-${size}" {
                controller = 'dbContainerImage'
                saction = 'index'
            }

    "/images/${imageId}" {
            controller = 'dbContainerImage'
            saction = 'index'
        }


		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}

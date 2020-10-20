package micronaut.example.mutate

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/sample")
class NettyMutableController {

    @Get("/mutatetwice")
    fun mutateNettyRequestTwice(request: HttpRequest<*>): HttpResponse<String> {
        request.mutate().mutate()

        return HttpResponse.ok()
    }

    @Get("/mutateonce")
    fun mutateNettyRequestOnce(request: HttpRequest<*>): HttpResponse<String> {
        request.mutate()
        return HttpResponse.ok()
    }
}
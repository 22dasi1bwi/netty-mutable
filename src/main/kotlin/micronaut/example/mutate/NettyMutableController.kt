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

    @Get("/mutateUriTwice")
    fun mutateUriOfNettyRequestTwice(request: HttpRequest<*>): HttpResponse<String> {
        val mutatedRequest = request.mutate().uri {
            it.path("first-mutate")
        }.mutate().uri {
            it.path("second-mutate")
        }
        return HttpResponse.ok(mutatedRequest.path)
    }

    @Get("/mutateonce")
    fun mutateNettyRequestOnce(request: HttpRequest<*>): HttpResponse<String> {
        request.mutate()
        return HttpResponse.ok()
    }
}
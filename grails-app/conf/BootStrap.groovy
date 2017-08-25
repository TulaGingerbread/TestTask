import testtask.College

class BootStrap {

    def init = { servletContext ->
        new College(name: 'College #1').save()
        new College(name: 'College #2').save()
        new College(name: 'College #3').save()
        new College(name: 'College #4').save()
    }
    def destroy = {
    }
}

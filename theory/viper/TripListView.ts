class TripListView {

    @observed_object
    presenter: TripListPresenter

    constructor(presenter: TripListPresenter) {
        this.presenter = presenter
    }

    body() {
        this.presenter.trips.forEach(each => {
            console.log(each)
        })
    }

    static Create() {
        let mdl = new DataModel()
        let interactor = new TripListInteractor(mdl)
        let presenter = new TripListPresenter(interactor)
        return new TripListView(presenter)
    }
}
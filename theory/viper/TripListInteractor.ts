class TripListInteractor {

    model: DataModel

    constructor(model: DataModel) {
        this.model = model
    }

    addNewTrip() {
        this.model.pushNewTrip()
    }
}

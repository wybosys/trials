class TripListPresenter extends ObservableObject {
    private interactor: TripListInteractor

    constructor(interactor: TripListInteractor) {
        super()
        this.interactor = interactor

        //interactor.model.$trips
        //.assign(to: \.trips, on: self)
        //.store(in: &cancellables)        
    }

    @published
    trips: Trip[] = []

    private cancelables = Set<AnyCancellable>()
}
This Repo holds a test project that I build for TradeMe, showing them how I would use their api to perform some basic browsing and drilling down behaviour.

The networking layer has been set up to make calls to get details for an individual listing, but these have not been wired up for views.

Generally I would use some kind of MVP architecture for the Fragments, however in this case the Fragments were pretty dumb and it seemed like overkill.

The uses a different MainActivity layout for handsets and tablets.  Tablets are displayed with a master detail view.

The project was a good chance for me to use Retrofit and Dagger2 for the first time, however the overhead of setting up these two libraries did mean that more time couldn’t be spent in other areas such as unit tests, fine tuning UI and how the app interacts with users.
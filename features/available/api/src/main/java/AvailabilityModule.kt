import com.example.d2_p1.available.data.AvailabilityRepositoryImpl
import com.example.d2_p1.available.data.SpaceRepositoryImplementation
import com.example.d2_p1.available.domain.AvailabilityRepository
import com.example.d2_p1.available.domain.SpaceRepository
import org.koin.dsl.module

val AvailabilityModule = module {
    single<SpaceRepository> { SpaceRepositoryImplementation() }
    single<AvailabilityRepository> { AvailabilityRepositoryImpl() }
}
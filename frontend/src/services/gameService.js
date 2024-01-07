import axios from 'axios';
import { apiRoutes } from '../routes/routeDefinitions';


/**
 * Accesses the backend API to get the list of agents
 * @returns {Promise} Resolves to an array of agents
 */
const getAgents = async () => {
  try {
    console.log("Getting Agent");
    const response = await axios.get(apiRoutes.getAgents);

    // Update state with response data
    console.log("Agent:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error getting agent:", error);
    throw error;
  }
}



export  { getAgents };